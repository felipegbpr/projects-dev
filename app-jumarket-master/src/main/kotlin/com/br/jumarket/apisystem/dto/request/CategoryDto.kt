package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Category
import jakarta.validation.constraints.NotEmpty


data class CategoryDto(
    @field:NotEmpty(message = "Invalid Input") val name: String,
) {
    fun toEntity(): Category = Category(
        name = this.name,
    )
}
