package com.br.jumarket.apisystem.controller

import com.br.jumarket.apisystem.dto.request.CategoryDto
import com.br.jumarket.apisystem.dto.request.CategoryUpdateDto
import com.br.jumarket.apisystem.dto.response.CategoryView
import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.service.impl.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping
    fun saveCategory(@RequestBody @Valid categoryDto: CategoryDto): ResponseEntity<CategoryView> {
        val savedCategory: Category = this.categoryService.save(categoryDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryView(savedCategory))
    }

    @GetMapping("/all")
    fun findAllCategories(): List<Category> {
        return categoryService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CategoryView> {
        val category: Category = this.categoryService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CategoryView(category))
    }

    @PatchMapping
    fun updateCategory(
        @RequestParam(value = "categoryId") id : Long,
        @RequestBody @Valid categoryUpdateDto: CategoryUpdateDto
    ) : ResponseEntity<CategoryView> {
        val category: Category = this.categoryService.findById(id)
        val categoryToUpdate: Category = categoryUpdateDto.toEntity(category)
        val categoryUpdated: Category = this.categoryService.save(categoryToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CategoryView(categoryUpdated))
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Long) = this.categoryService.delete(id)

}