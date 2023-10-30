package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.enumeration.PaymentType
import jakarta.validation.constraints.NotNull

data class ShoppingCartDto(
    @field:NotNull(message = "Invalid input") val productName: String,
    @field:NotNull(message = "Invalid input") val quantityItems: Int,
    @field:NotNull(message = "Invalid input") val salePrice: Float,
    @field:NotNull(message = "Invalid input") val paymentType: String
) {
    fun toEntity(): ShoppingCart = ShoppingCart(
        product = Product(nameProduct = this.productName),
        quantityItems = this.quantityItems,
        salePrice = this.salePrice,
        paymentType = this.paymentType
    )
}
