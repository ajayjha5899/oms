package com.ajayjha5899.oms.service.impl

import com.ajayjha5899.oms.dto.CreateItemRequestDTO
import com.ajayjha5899.oms.dto.GetItemResponseDTO
import com.ajayjha5899.oms.dto.PageData
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.entity.ItemEntity
import com.ajayjha5899.oms.enum.PriceComparisonEnum
import com.ajayjha5899.oms.exception.BadRequestException
import com.ajayjha5899.oms.repository.ItemRepository
import com.ajayjha5899.oms.service.ItemService
import com.ajayjha5899.oms.utils.Constants
import com.ajayjha5899.oms.utils.DTOUtils
import com.ajayjha5899.oms.utils.ItemServiceUtils
import com.ajayjha5899.oms.utils.helpers.GetItemParams
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.Optional

@Service
class ItemServiceImpl (val itemRepository: ItemRepository): ItemService {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ItemServiceImpl::class.java)
    }

    override fun createItem(dto: CreateItemRequestDTO): ResponseDTO {
        try {
            val entity = ItemEntity(
                name = dto.name ?: throw BadRequestException("Item name cannot be null"),
                price = dto.price ?: throw BadRequestException("Item price cannot be null"),
                createdOn = ZonedDateTime.now(),
                updatedOn = ZonedDateTime.now()
            )
            val savedEntity = itemRepository.save(entity)

            return ResponseDTO(
                meta = DTOUtils.getSuccessMetaData("Item created with id: ${savedEntity.id}")
            )
        } catch (e: Exception) {
            logger.error("Error while creating item. createItemDTO: $dto")
            throw e
        }
    }

    override fun getItem(getItemParams: GetItemParams): ResponseDTO {
        try {
            val page = getItemParams.page ?: 1L
            val elementsPerPage = if (getItemParams.elementsPerPage == 0L) throw BadRequestException("Elements per page should be greater than 0")
            else getItemParams.elementsPerPage ?: Constants.Page.elementsPerPage

            if (null != getItemParams.id) {
                logger.info("Fetching items by id")
                try {
                    val item: Optional<ItemEntity> = itemRepository.findById(getItemParams.id)

                    val body: List<GetItemResponseDTO> = listOf(ItemServiceUtils.itemEntityToGetItemResponseDTO(item.get()))
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData(),
                        pageData = PageData(
                            totalPages = 1L,
                            elementsPerPage = elementsPerPage,
                            currentPage = 1L
                        ),
                        body = body
                    )
                } catch (e: NoSuchElementException) {
                    logger.info("No item found with id: ${getItemParams.id}")
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData("No item found with id: ${getItemParams.id}")
                    )
                }
            }

            else if (!getItemParams.name.isNullOrBlank() && (null == getItemParams.price || getItemParams.priceComparison.isNullOrBlank())) {
                logger.info("Fetching items by name only")

                val pageable: Pageable = PageRequest.of(page.toInt() - 1, elementsPerPage.toInt())
                val items: Page<ItemEntity> = itemRepository.findLikeName(getItemParams.name, pageable)

                if (items.isEmpty) {
                    logger.info("No item found like name: ${getItemParams.name}")
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData("No item found like name: ${getItemParams.name}")
                    )
                }

                val body: List<GetItemResponseDTO> = ItemServiceUtils.itemEntityToGetItemResponseDTO(items)
                return ResponseDTO(
                    meta = DTOUtils.getSuccessMetaData(),
                    pageData = PageData(
                        totalPages = items.totalPages.toLong(),
                        elementsPerPage = pageable.pageSize.toLong(),
                        currentPage = pageable.pageNumber + 1L
                    ),
                    body = body
                )
            }

            else if (getItemParams.name.isNullOrBlank() && (null != getItemParams.price && !getItemParams.priceComparison.isNullOrBlank())) {
                logger.info("Fetching items by price only")

                val pageable: Pageable = PageRequest.of(page.toInt() - 1, elementsPerPage.toInt())
                val items: Page<ItemEntity> = when(getItemParams.priceComparison) {
                    PriceComparisonEnum.GREATER.value -> itemRepository.findByPriceGreaterThan(getItemParams.price, pageable)
                    PriceComparisonEnum.GREATER_OR_EQUAL.value -> itemRepository.findByPriceGreaterThanOrEqualTo(getItemParams.price, pageable)
                    PriceComparisonEnum.LESSER.value -> itemRepository.findByPriceLesserThan(getItemParams.price, pageable)
                    PriceComparisonEnum.LESSER_OR_EQUAL.value -> itemRepository.findByPriceLesserThanOrEqualTo(getItemParams.price, pageable)
                    PriceComparisonEnum.EQUAL.value -> itemRepository.findByPrice(getItemParams.price, pageable)
                    else -> throw BadRequestException("Invalid priceComparison: ${getItemParams.priceComparison}")
                }

                if (items.isEmpty) {
                    logger.info("No item found with priceComparison: ${getItemParams.priceComparison} and price: ${getItemParams.price}")
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData("No item found with priceComparison: ${getItemParams.priceComparison} and price: ${getItemParams.price}")
                    )
                }

                val body: List<GetItemResponseDTO> = ItemServiceUtils.itemEntityToGetItemResponseDTO(items)
                return ResponseDTO(
                    meta = DTOUtils.getSuccessMetaData(),
                    pageData = PageData(
                        totalPages = items.totalPages.toLong(),
                        elementsPerPage = pageable.pageSize.toLong(),
                        currentPage = pageable.pageNumber + 1L
                    ),
                    body = body
                )
            }

            else if (!getItemParams.name.isNullOrBlank() && null != getItemParams.price && !getItemParams.priceComparison.isNullOrBlank()) {
                logger.info("Fetching items by name and price")

                val pageable: Pageable = PageRequest.of(page.toInt() - 1, elementsPerPage.toInt())
                val items: Page<ItemEntity> = when(getItemParams.priceComparison) {
                    PriceComparisonEnum.GREATER.value -> itemRepository.findByNameAndPriceGreaterThan(getItemParams.name, getItemParams.price, pageable)
                    PriceComparisonEnum.GREATER_OR_EQUAL.value -> itemRepository.findByNameAndPriceGreaterThanOrEqualTo(getItemParams.name, getItemParams.price, pageable)
                    PriceComparisonEnum.LESSER.value -> itemRepository.findByNameAndPriceLesserThan(getItemParams.name, getItemParams.price, pageable)
                    PriceComparisonEnum.LESSER_OR_EQUAL.value -> itemRepository.findByNameAndPriceLesserThanOrEqualTo(getItemParams.name, getItemParams.price, pageable)
                    PriceComparisonEnum.EQUAL.value -> itemRepository.findNameAndByPrice(getItemParams.name, getItemParams.price, pageable)
                    else -> throw BadRequestException("Invalid priceComparison: ${getItemParams.priceComparison}")
                }

                if (items.isEmpty) {
                    logger.info("No item found like name: ${getItemParams.name} with priceComparison: ${getItemParams.priceComparison} and price: ${getItemParams.price}")
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData("No item found like name: ${getItemParams.name} with priceComparison: ${getItemParams.priceComparison} and price: ${getItemParams.price}")
                    )
                }

                val body: List<GetItemResponseDTO> = ItemServiceUtils.itemEntityToGetItemResponseDTO(items)
                return ResponseDTO(
                    meta = DTOUtils.getSuccessMetaData(),
                    pageData = PageData(
                        totalPages = items.totalPages.toLong(),
                        elementsPerPage = pageable.pageSize.toLong(),
                        currentPage = pageable.pageNumber + 1L
                    ),
                    body = body
                )
            }

            else if (getItemParams.name.isNullOrBlank() && null == getItemParams.price && getItemParams.priceComparison.isNullOrBlank()){
                logger.info("Fetching all items")

                val pageable: Pageable = PageRequest.of(page.toInt() - 1, elementsPerPage.toInt())
                val items: Page<ItemEntity> = itemRepository.findAll(pageable)
                if (items.isEmpty) {
                    logger.info("No item found")
                    return ResponseDTO(
                        meta = DTOUtils.getSuccessMetaData("No item found")
                    )
                }

                val body: List<GetItemResponseDTO> = ItemServiceUtils.itemEntityToGetItemResponseDTO(items)
                return ResponseDTO(
                    meta = DTOUtils.getSuccessMetaData(),
                    pageData = PageData(
                        totalPages = items.totalPages.toLong(),
                        elementsPerPage = pageable.pageSize.toLong(),
                        currentPage = pageable.pageNumber + 1L
                    ),
                    body = body
                )
            }
            
            throw BadRequestException("No items found with the given params")

        } catch (e: Exception) {
            logger.error("Error while fetching item. getItemParams: $getItemParams")
            throw e
        }
    }
}