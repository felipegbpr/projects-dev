package com.br.jumarket.apisystem.dto.response

import com.br.jumarket.apisystem.entity.Category

data class CategoryView(
    val id: Long?,
    val name: String
) {
    constructor(category: Category): this (
        id = category.id,
        name = category.name
    )
}
