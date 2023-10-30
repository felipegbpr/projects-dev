package com.br.jumarket.apisystem.service.impl

import com.br.jumarket.apisystem.dto.response.CategoryView
import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.CategoryRespository
import com.br.jumarket.apisystem.service.ICategoryService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRespository: CategoryRespository
) : ICategoryService {
    override fun save(category: Category): Category = this.categoryRespository.save(category)

    override fun findAll(): List<Category> {
        return this.categoryRespository.findAll();
    }

    override fun findById(id: Long): Category = this.categoryRespository.findById(id)
        .orElseThrow{ throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val category: Category = this.findById(id)
        this.categoryRespository.delete(category)
    }

}