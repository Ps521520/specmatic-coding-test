package com.store.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductId @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
)