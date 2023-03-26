package com.ajayjha5899.oms.service

import com.ajayjha5899.oms.dto.CreateItemDTO
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.repository.ItemRepository

interface ItemService {
    fun createItem(dto: CreateItemDTO): ResponseDTO
}