package com.ocadotechnology.sttp.oauth2

import com.ocadotechnology.sttp.oauth2.Introspection.TokenIntrospectionResponse
import com.ocadotechnology.sttp.oauth2.common.Scope
import io.circe.parser.decode
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.OptionValues

import java.time.Instant

class IntrospectionSerializationSpec extends AnyWordSpec with Matchers with OptionValues {
  "Token" should {
    "deserialize token introspection response" in {
      val clientId = "Client ID"
      val domain = "mock"
      val exp = Instant.EPOCH
      val active = false
      val authority1 = "aaa"
      val authority2 = "bbb"
      val authorities = List(authority1, authority2)
      val scope = "cfc.first-app_scope"
      val tokenType = "Bearer"
      val audience = "Aud1"

      val json =
        s"""{
            "client_id": "$clientId",
            "domain": "$domain",
            "exp": ${exp.getEpochSecond},
            "active": $active,
            "authorities": [ "$authority1", "$authority2" ],
            "scope": "$scope",
            "token_type": "$tokenType",
            "aud": "$audience"
          }"""

      decode[TokenIntrospectionResponse](json) shouldBe Right(
        TokenIntrospectionResponse(
          active = active,
          clientId = Some(clientId),
          domain = Some(domain),
          exp = Some(exp),
          authorities = Some(authorities),
          scope = Some(Scope.of(scope).value),
          tokenType = Some(tokenType),
          aud = Some(Introspection.StringAudience(audience))
        )
      )

    }
  }
}
