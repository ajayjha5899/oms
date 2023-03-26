package com.ajayjha5899.oms.repository

import com.ajayjha5899.oms.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<ItemEntity, Long> {
}