package com.ajayjha5899.oms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
        @field:JsonProperty("timestamp")
        val timestamp: String,

        @field:JsonProperty("message")
        val message: String,

        @field:JsonProperty("code")
        val code: Int
)
