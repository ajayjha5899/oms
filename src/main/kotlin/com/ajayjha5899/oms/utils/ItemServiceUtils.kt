package com.ajayjha5899.oms.utils

import com.ajayjha5899.oms.dto.GetItemResponseDTO
import com.ajayjha5899.oms.entity.ItemEntity
import com.ajayjha5899.oms.exception.NullNotAllowedException
import org.springframework.data.domain.Page

object ItemServiceUtils {

    fun itemEntityToGetItemResponseDTO(entity: ItemEntity): GetItemResponseDTO = GetItemResponseDTO(
        id = entity.id ?: throw NullNotAllowedException("ItemEntity cannot have null id"),
        name = entity.name,
        price = entity.price,
        createdOn = entity.createdOn,
        updatedOn = entity.updatedOn
    )

    fun itemEntityToGetItemResponseDTO(entityList: List<ItemEntity>): List<GetItemResponseDTO> {
        val responseDTO: MutableList<GetItemResponseDTO> = mutableListOf()

        entityList.forEach { entity ->
            responseDTO.add(
                GetItemResponseDTO(
                    id = entity.id ?: throw NullNotAllowedException("ItemEntity cannot have null id"),
                    name = entity.name,
                    price = entity.price,
                    createdOn = entity.createdOn,
                    updatedOn = entity.updatedOn
                )
            )
        }

        return responseDTO
    }

    fun itemEntityToGetItemResponseDTO(entityPage: Page<ItemEntity>): List<GetItemResponseDTO> {
        val responseDTO: MutableList<GetItemResponseDTO> = mutableListOf()

        entityPage.forEach{ entity ->
            responseDTO.add(
                GetItemResponseDTO(
                    id = entity.id ?: throw NullNotAllowedException("ItemEntity cannot have null id"),
                    name = entity.name,
                    price = entity.price,
                    createdOn = entity.createdOn,
                    updatedOn = entity.updatedOn
                )
            )
        }

        return responseDTO
    }
}