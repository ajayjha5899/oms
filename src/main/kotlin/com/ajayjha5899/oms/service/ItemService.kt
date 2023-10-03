package com.ajayjha5899.oms.service

import com.ajayjha5899.oms.dto.CreateItemRequestDTO
import com.ajayjha5899.oms.dto.ResponseDTO
import com.ajayjha5899.oms.utils.helpers.GetItemParams

interface ItemService {
    fun createItem(dto: CreateItemRequestDTO): ResponseDTO

    fun getItem(getItemParams: GetItemParams): ResponseDTO
}