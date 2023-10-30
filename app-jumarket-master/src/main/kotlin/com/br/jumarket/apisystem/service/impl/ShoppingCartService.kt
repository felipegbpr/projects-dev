package com.br.jumarket.apisystem.service.impl

import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.ShoppingCartRepository
import com.br.jumarket.apisystem.service.IShoppingCartService
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
    private val shoppingCartRepository: ShoppingCartRepository
): IShoppingCartService {
    override fun save (shoppingCart: ShoppingCart): ShoppingCart = this.shoppingCartRepository.save(shoppingCart)

    override fun findById(id: Long): ShoppingCart = this.shoppingCartRepository.findById(id)
        .orElseThrow{ throw BusinessException("Id $id not found") }

    override fun delete(id: Long) {
        val shoppingCart: ShoppingCart = this.findById(id)
        this.shoppingCartRepository.delete(shoppingCart)
    }

}