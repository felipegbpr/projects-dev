package com.br.jumarket.apisystem.dto.request

import com.br.jumarket.apisystem.entity.Category
import jakarta.validation.constraints.NotEmpty

data class CategoryUpdateDto(
    @field:NotEmpty(message = "Invalid input") val name: String
) {
    fun toEntity(category: Category): Category {
        category.name = this.name
        return category
    }
}