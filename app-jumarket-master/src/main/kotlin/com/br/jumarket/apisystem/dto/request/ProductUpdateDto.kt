package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class ProductUpdateDto(
    @field:NotEmpty(message = "Invalid input") val nameProduct: String,
    @field:NotEmpty(message = "Invalid input") val unitMeasurement: String,
    @field:NotNull(message = "Invalid input") val unitPrice: Float
) {
    fun toEntity(product: Product): Product {
        product.nameProduct = this.nameProduct
        product.unitMeasurement = this.unitMeasurement
        product.unitPrice = this.unitPrice
        return product
    }
}
