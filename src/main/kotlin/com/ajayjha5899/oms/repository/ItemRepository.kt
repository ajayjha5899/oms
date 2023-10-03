package com.ajayjha5899.oms.repository

import com.ajayjha5899.oms.entity.ItemEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<ItemEntity, Long> {

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%'",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%'", nativeQuery = true)
    fun findLikeName(@Param("name") name: String, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE price > :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE price > :price", nativeQuery = true)
    fun findByPriceGreaterThan(@Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE price >= :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE price >= :price", nativeQuery = true)
    fun findByPriceGreaterThanOrEqualTo(@Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE price < :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE price < :price", nativeQuery = true)
    fun findByPriceLesserThan(@Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE price <= :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE price <= :price", nativeQuery = true)
    fun findByPriceLesserThanOrEqualTo(@Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    fun findByPrice(@Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%' AND price > :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%' AND price > :price", nativeQuery = true)
    fun findByNameAndPriceGreaterThan(@Param("name") name: String, @Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%' AND price >= :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%' AND price >= :price", nativeQuery = true)
    fun findByNameAndPriceGreaterThanOrEqualTo(@Param("name") name: String, @Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%' AND price < :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%' AND price < :price", nativeQuery = true)
    fun findByNameAndPriceLesserThan(@Param("name") name: String, @Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%' AND price <= :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%' AND price <= :price", nativeQuery = true)
    fun findByNameAndPriceLesserThanOrEqualTo(@Param("name") name: String, @Param("price") price: Double, pageable: Pageable): Page<ItemEntity>

    @Query(value = "SELECT * FROM items WHERE name LIKE '%:name%' AND price = :price",
        countQuery = "SELECT COUNT(*) FROM items WHERE name LIKE '%:name%' AND price = :price", nativeQuery = true)
    fun findNameAndByPrice(@Param("name") name: String, @Param("price") price: Double, pageable: Pageable): Page<ItemEntity>
}