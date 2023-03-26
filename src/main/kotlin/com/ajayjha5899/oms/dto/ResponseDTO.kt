package com.ajayjha5899.oms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO (
        @field:JsonProperty("meta")
        private val meta: MetaData,

        @field:JsonProperty("body")
        private val body: Any?
)