package com.ajayjha5899.oms.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseDTO (
        @field:JsonProperty("meta")
        private val meta: MetaData,

        @field:JsonProperty("pageData")
        private val pageData: PageData? = null,

        @field:JsonProperty("body")
        private val body: Any? = null
)