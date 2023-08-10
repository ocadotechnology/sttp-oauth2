package com.ocadotechnology.sttp.oauth2.json

import sttp.client4.ResponseAs
import sttp.client4.ResponseException
import sttp.client4.asString

object SttpJsonSupport {
  def asJson[B: JsonDecoder]: ResponseAs[Either[ResponseException[String, JsonDecoder.Error], B]] =
    asString.mapWithMetadata(ResponseAs.deserializeRightWithError(JsonDecoder[B].decodeString)).showAs("either(as string, as json)")
}
