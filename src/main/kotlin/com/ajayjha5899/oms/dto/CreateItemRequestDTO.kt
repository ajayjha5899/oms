package com.ajayjha5899.oms.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class CreateItemRequestDTO(
    @field:JsonProperty("name")
    val name: String?,

    @field:JsonProperty("price")
    val price: Double?
)
