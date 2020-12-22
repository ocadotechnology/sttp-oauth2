package com.ocadotechnology.sttp.oauth2

import cats.implicits._
import cats.Functor
import eu.timepit.refined.types.string.NonEmptyString
import sttp.client.NothingT
import sttp.client.SttpBackend
import sttp.client.basicRequest
import sttp.model.Uri
import com.ocadotechnology.sttp.oauth2.common._

object ClientCredentials {

  /** Requests token from OAuth2 provider `tokenUri` using `clientId`, `clientSecret`, requested `scope` and `client_credentials` grant type.
    * Request is performed with provided `sttpBackend`.
    *
    * All errors are mapped to [[common.Error]] ADT.
    */
  def requestToken[F[_]: Functor](
    tokenUri: Uri,
    clientId: NonEmptyString,
    clientSecret: Secret[String],
    scope: Scope
  )(
    sttpBackend: SttpBackend[F, Nothing, NothingT]
  ): F[ClientCredentialsToken.Response] = {
    implicit val backend: SttpBackend[F, Nothing, NothingT] = sttpBackend
    basicRequest
      .post(tokenUri)
      .body(requestTokenParams(clientId, clientSecret, scope))
      .response(ClientCredentialsToken.response)
      .send()
      .map(_.body)
  }

  private def requestTokenParams(clientId: NonEmptyString, clientSecret: Secret[String], scope: Scope) =
    Map(
      "grant_type" -> "client_credentials",
      "client_id" -> clientId.value,
      "client_secret" -> clientSecret.value,
      "scope" -> scope.value
    )

  /** Introspects provided `token` in OAuth2 provider `tokenIntrospectionUri`, using `clientId` and `clientSecret`.
    * Request is performed with provided `sttpBackend`.
    *
    * Errors are mapped to [[common.Error]] ADT.
    */
  def introspectToken[F[_]: Functor](
    tokenIntrospectionUri: Uri,
    clientId: NonEmptyString,
    clientSecret: Secret[String],
    token: Secret[String]
  )(
    sttpBackend: SttpBackend[F, Nothing, NothingT]
  ): F[Introspection.Response] = {
    implicit val backend: SttpBackend[F, Nothing, NothingT] = sttpBackend
    basicRequest
      .post(tokenIntrospectionUri)
      .body(requestTokenIntrospectionParams(clientId, clientSecret, token))
      .response(Introspection.response)
      .send()
      .map(_.body)
  }

  private def requestTokenIntrospectionParams(clientId: NonEmptyString, clientSecret: Secret[String], token: Secret[String]) =
    Map(
      "client_id" -> clientId.value,
      "client_secret" -> clientSecret.value,
      "token" -> token.value
    )

}
