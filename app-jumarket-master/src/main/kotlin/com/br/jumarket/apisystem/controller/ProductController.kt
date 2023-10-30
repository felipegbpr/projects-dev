package com.br.jumarket.apisystem.controller

import com.br.jumarket.apisystem.dto.request.ProductDto
import com.br.jumarket.apisystem.dto.request.ProductUpdateDto
import com.br.jumarket.apisystem.dto.response.ProductView
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.service.impl.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
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
@RequestMapping("/api/products")
class ProductController(
    val productService: ProductService
) {
    @PostMapping
    fun saveProduct(@RequestBody @Valid productDto: ProductDto): ResponseEntity<ProductView> {
        val savedProduct: Product = this.productService.save(productDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductView(savedProduct))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ProductView> {
        val product: Product = this.productService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(ProductView(product))
    }

    @PatchMapping
    fun updatedProduct(
       @RequestParam(value = "productId") id: Long,
       @RequestBody @Valid productUpdateDto: ProductUpdateDto
    ): ResponseEntity<ProductView> {
        val product: Product = this.productService.findById(id)
        val productToUpdate: Product = productUpdateDto.toEntity(product)
        val productUpdated: Product = this.productService.save(product)
        return ResponseEntity.status(HttpStatus.OK).body(ProductView(productUpdated))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduct(@PathVariable id: Long) = this.productService.delete(id)

}