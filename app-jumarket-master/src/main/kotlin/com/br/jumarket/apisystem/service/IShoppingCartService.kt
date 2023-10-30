package com.br.jumarket.apisystem.service

import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.entity.ShoppingCart

interface IShoppingCartService {

    fun save(product: ShoppingCart): ShoppingCart

    fun findById(id: Long): ShoppingCart

    fun delete(id: Long)

}