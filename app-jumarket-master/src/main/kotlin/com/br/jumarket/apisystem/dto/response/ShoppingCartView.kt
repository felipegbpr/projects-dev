package com.br.jumarket.apisystem.dto.response

import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.enumeration.PaymentType

data class ShoppingCartView(
    val product: String?,
    val quantityItems: Int,
    val salePrice: Float,
    val paymentType: String,
    val id: Long?,

) {
    constructor(shoppingCart: ShoppingCart): this (
        product = shoppingCart.product?.nameProduct,
        quantityItems = shoppingCart.quantityItems,
        salePrice = shoppingCart.salePrice,
        paymentType = shoppingCart.paymentType,
        id = shoppingCart.id,
    )
}
