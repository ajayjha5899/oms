package com.ajayjha5899.oms.controller

import com.ajayjha5899.oms.dto.CreateItemDTO
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.exception.controller.ExceptionController
import com.ajayjha5899.oms.service.ItemService
import com.ajayjha5899.oms.utils.Constants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
    private fun createItem(@RequestBody dto: CreateItemDTO): ResponseDTO {
        return itemService.createItem(dto)
    }
}