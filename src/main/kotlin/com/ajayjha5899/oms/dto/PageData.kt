package com.ajayjha5899.oms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class PageData(

    @field:JsonProperty("totalPages")
    val totalPages: Long,

    @field:JsonProperty("elementsPerPage")
    val elementsPerPage: Long,

    @field:JsonProperty("currentPage")
    val currentPage: Long
)
