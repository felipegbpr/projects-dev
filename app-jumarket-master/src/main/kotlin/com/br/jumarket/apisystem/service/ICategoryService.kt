package com.br.jumarket.apisystem.service

import com.br.jumarket.apisystem.dto.response.CategoryView
import com.br.jumarket.apisystem.entity.Category

interface ICategoryService {

    fun save(category: Category): Category
    fun findAll(): List<Category>
    fun findById(id: Long): Category
    fun delete(id: Long)
}