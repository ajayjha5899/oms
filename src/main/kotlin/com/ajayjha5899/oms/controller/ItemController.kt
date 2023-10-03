package com.ajayjha5899.oms.controller

import com.ajayjha5899.oms.dto.CreateItemRequestDTO
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.exception.controller.ExceptionController
import com.ajayjha5899.oms.service.ItemService
import com.ajayjha5899.oms.utils.Constants
import com.ajayjha5899.oms.utils.helpers.GetItemParams
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.Endpoints.apiV1)
class ItemController {

    private val logger: Logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @Autowired
    lateinit var itemService: ItemService

    @PostMapping(Constants.Endpoints.item)
    @ResponseBody
    private fun createItem(
        @RequestBody dto: CreateItemRequestDTO
    ): ResponseDTO {
        return itemService.createItem(dto)
    }

    @GetMapping(Constants.Endpoints.item)
    private fun getItem(
        @RequestParam("page") page: Long?,
        @RequestParam("elementsPerPage") elementsPerPage: Long?,
        @RequestParam("id") id: Long?,
        @RequestParam("name") name: String?,
        @RequestParam("price") price: Double?,
        @RequestParam("priceComparison") priceComparison: String?
    ): ResponseDTO {
        val getItemParams: GetItemParams = GetItemParams.Builder()
            .page(page)
            .elementsPerPage(elementsPerPage)
            .id(id)
            .name(name)
            .price(price)
            .priceComparison(priceComparison)
            .build()
        return itemService.getItem(getItemParams)
    }
}