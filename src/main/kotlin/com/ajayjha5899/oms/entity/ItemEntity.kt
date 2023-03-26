package com.ajayjha5899.oms.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "items")
data class ItemEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    val id: Long? = null,

    @Column(name = "name", unique = false, nullable = false, updatable = true)
    val name: String,

    @Column(name = "price", unique = false, nullable = false, updatable = true)
    val price: Double,

    @Column(name = "created_on", unique = false, nullable = false, updatable = true)
    @CreationTimestamp
    val createdOn: ZonedDateTime,

    @Column(name = "updated_on", unique = false, nullable = false, updatable = true)
    @UpdateTimestamp
    val updatedOn: ZonedDateTime
)