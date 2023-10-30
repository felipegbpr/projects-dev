package com.br.jumarket.apisystem.repository

import com.br.jumarket.apisystem.entity.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingCartRepository: JpaRepository<ShoppingCart, Long>