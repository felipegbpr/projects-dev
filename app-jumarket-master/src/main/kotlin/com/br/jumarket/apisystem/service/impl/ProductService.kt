package com.br.jumarket.apisystem.service.impl

import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.ProductRepository
import com.br.jumarket.apisystem.service.IProductService
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
): IProductService {
    override fun save (product: Product): Product = this.productRepository.save(product)

    override fun findAll(): List<Product> {
        return this.productRepository.findAll();
    }

    override fun findById(id: Long): Product = this.productRepository.findById(id)
        .orElseThrow{ throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val product: Product = this.findById(id)
        this.productRepository.delete(product)
    }

}