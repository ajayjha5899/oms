package com.ajayjha5899.oms.dto

import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class GetItemResponseDTO(
    val id: Long,
    val name: String,
    val price: Double,
    val createdOn: ZonedDateTime,
    val updatedOn: ZonedDateTime
)
