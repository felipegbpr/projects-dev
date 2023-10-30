package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.entity.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class ProductDto(
    @field:NotEmpty(message = "Invalid Input") val nameProduct: String,
    @field:NotEmpty(message = "Invalid Input") val unitMeasurement: String,
    @field:NotNull(message = "Invalid Input") val unitPrice: Float,
    @field:NotNull(message = "Invalid Input") val categoryId: Long,
) {
    fun toEntity(): Product = Product(
        nameProduct = this.nameProduct,
        unitMeasurement = this.unitMeasurement,
        unitPrice = this.unitPrice,
        category = Category(id = this.categoryId)
    )
}
