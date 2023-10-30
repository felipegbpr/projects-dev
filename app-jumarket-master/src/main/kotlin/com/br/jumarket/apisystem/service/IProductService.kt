package com.br.jumarket.apisystem.service

import com.br.jumarket.apisystem.entity.Product

interface IProductService {

    fun save(product: Product): Product

    fun findAll(): List<Product>

    fun findById(id: Long): Product

    fun delete(id: Long)

}