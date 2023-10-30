package com.br.jumarket.apisystem.dto.response


import com.br.jumarket.apisystem.entity.Product

data class ProductView(
    val nameProduct: String,
    val unitMeasurement: String,
    val unitPrice: Float,
    val id: Long?,

) {
    constructor(product: Product): this (
        nameProduct = product.nameProduct,
        unitMeasurement = product.unitMeasurement,
        unitPrice = product.unitPrice,
        id = product.id,
    )
}
