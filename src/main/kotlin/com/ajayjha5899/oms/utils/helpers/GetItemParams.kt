package com.ajayjha5899.oms.utils.helpers

class GetItemParams private constructor(
    val page: Long?,
    val elementsPerPage: Long?,
    val id: Long?,
    val name: String?,
    val price: Double?,
    val priceComparison: String?
) {
    data class Builder(
        var page: Long? = null,
        var elementsPerPage: Long? = null,
        var id: Long? = null,
        var name: String? = null,
        var price: Double? = null,
        var priceComparison: String? = null
    ) {
        fun page(page: Long?) = apply { this.page = page }
        fun elementsPerPage(elementsPerPage: Long?) = apply { this.elementsPerPage = elementsPerPage }
        fun id(id: Long?) = apply { this.id = id }
        fun name(name: String?) = apply { this.name = name }
        fun price(price: Double?) = apply { this.price = price }
        fun priceComparison(priceComparison: String?) = apply { this.priceComparison = priceComparison }
        fun build() = GetItemParams(this.page, this.elementsPerPage, this.id, this.name, this.price, this.priceComparison)
    }
}