package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.enumeration.PaymentType
import jakarta.validation.constraints.NotNull


data class ShoppingCartUpdateDto(
    @field:NotNull(message = "Invalid input") val product: Product,
    @field:NotNull(message = "Invalid input") val quantityItems: Int,
    @field:NotNull(message = "Invalid input") val salePrice: Float
) {
    fun toEntity(shoppingCart: ShoppingCart): ShoppingCart {
        shoppingCart.product = this.product
        shoppingCart.quantityItems = this.quantityItems
        shoppingCart.salePrice = this.salePrice
        return shoppingCart
    }
}
