package com.br.jumarket.apisystem.repository

import com.br.jumarket.apisystem.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long>