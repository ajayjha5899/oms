package com.ajayjha5899.oms.service.impl

import com.ajayjha5899.oms.dto.CreateItemDTO
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.entity.ItemEntity
import com.ajayjha5899.oms.exception.BadRequestException
import com.ajayjha5899.oms.repository.ItemRepository
import com.ajayjha5899.oms.service.ItemService
import com.ajayjha5899.oms.utils.DTOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ItemServiceImpl (val itemRepository: ItemRepository): ItemService {

    private val logger: Logger = LoggerFactory.getLogger(ItemServiceImpl::class.java)

    override fun createItem(dto: CreateItemDTO): ResponseDTO {
        try {
            val entity = ItemEntity(
                name = dto.name ?: throw BadRequestException("Item name cannot be null"),
                price = dto.price ?: throw BadRequestException("Item price cannot be null"),
                createdOn = ZonedDateTime.now(),
                updatedOn = ZonedDateTime.now()
            )
            val savedEntity = itemRepository.save(entity)

            return ResponseDTO(
                meta = DTOUtils.getSuccessMetaData("Item created with id: ${savedEntity.id}"),
                body = null
            )
        } catch (e: Exception) {
            logger.error("Error while creating item. createItemDTO: $dto")
            throw e
        }
    }
}