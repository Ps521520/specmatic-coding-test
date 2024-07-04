package com.store.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponseBody @JsonCreator constructor(
    @JsonProperty("timestamp") val timestamp: LocalDateTime,
    @JsonProperty("status") val status: Int,
    @JsonProperty("error") val error: String,
    @JsonProperty("path") val path: String
)