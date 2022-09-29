package com.ocadotechnology.sttp.oauth2.cache.cats

import cats.effect.kernel.MonadCancelThrow
import cats.data.OptionT
import cats.effect.kernel.Clock
import cats.effect.kernel.Concurrent
import cats.implicits._
import com.ocadotechnology.sttp.oauth2.Introspection.TokenIntrospectionResponse
import com.ocadotechnology.sttp.oauth2.Secret
import com.ocadotechnology.sttp.oauth2.TokenIntrospection
import com.ocadotechnology.sttp.oauth2.cache.ExpiringCache

import java.time.Instant
import scala.concurrent.duration._
import cats.effect.std.Semaphore

final class CachingTokenIntrospection[F[_]: Clock: MonadCancelThrow](
  delegate: TokenIntrospection[F],
  semaphore: Semaphore[F],
  cache: ExpiringCache[F, Secret[String], TokenIntrospectionResponse],
  defaultExpirationTime: FiniteDuration
) extends TokenIntrospection[F] {

  override def introspect(token: Secret[String]): F[TokenIntrospectionResponse] =
    getFromCache(token).flatMap {
      case Some(value) => value.pure[F]
      case None        => fetchAndCache(token)
    }

  private def getFromCache(token: Secret[String]): F[Option[TokenIntrospectionResponse]] = {
    for {
      now      <- OptionT.liftF(Clock[F].realTime) // Using realTime since it's available cross platform
      response <- OptionT(cache.get(token))
      result   <- OptionT.when[F, TokenIntrospectionResponse](responseIsUpToDate(now, response))(response)
    } yield result
  }.value

  private def fetchAndCache(token: Secret[String]): F[TokenIntrospectionResponse] =
    // semaphore prevents concurrent token introspection call
    semaphore
      .permit
      .surround(delegate.introspect(token))
      .flatTap { response =>
        cache.put(token, response, responseExpirationOrDefault(response))
      }

  private def responseIsUpToDate(now: FiniteDuration, response: TokenIntrospectionResponse): Boolean =
    response.exp.map(_.getNano > now.toNanos).getOrElse(true)

  private def responseExpirationOrDefault(response: TokenIntrospectionResponse): Instant =
    response
      .exp
      .getOrElse(Instant.ofEpochMilli(defaultExpirationTime.toMillis))

}

object CachingTokenIntrospection {

  def apply[F[_]: Concurrent: Clock](
    delegate: TokenIntrospection[F],
    cache: ExpiringCache[F, Secret[String], TokenIntrospectionResponse],
    defaultExpirationTime: FiniteDuration = 10.minutes
  ): F[CachingTokenIntrospection[F]] =
    Semaphore[F](n = 1).map(new CachingTokenIntrospection[F](delegate, _, cache, defaultExpirationTime))

}