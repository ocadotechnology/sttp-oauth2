package com.ocadotechnology.sttp.oauth2

import cats.MonadThrow
import common._
import com.ocadotechnology.sttp.oauth2.PasswordGrant.User
import eu.timepit.refined.types.string.NonEmptyString
import sttp.client3.SttpBackend
import sttp.model.Uri
import cats.syntax.all._

trait PasswordGrantProvider[F[_]] {
  def requestToken(user: User, scope: Scope): F[ExtendedOAuth2TokenResponse]
}

object PasswordGrantProvider {

  def apply[F[_]](implicit ev: PasswordGrantProvider[F]): PasswordGrantProvider[F] = ev

  def apply[F[_]: MonadThrow](
    tokenUrl: Uri,
    clientId: NonEmptyString,
    clientSecret: Secret[String]
  )(
    backend: SttpBackend[F, Any]
  ): PasswordGrantProvider[F] = { (user: User, scope: Scope) =>
    PasswordGrant.requestToken(tokenUrl, user, clientId, clientSecret, scope)(backend).map(_.leftMap(OAuth2Exception.apply)).rethrow
  }

}
