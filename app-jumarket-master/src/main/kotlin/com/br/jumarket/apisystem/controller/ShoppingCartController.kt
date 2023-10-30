package com.br.jumarket.apisystem.controller

import com.br.jumarket.apisystem.dto.request.ShoppingCartDto
import com.br.jumarket.apisystem.dto.request.ShoppingCartUpdateDto
import com.br.jumarket.apisystem.dto.response.ShoppingCartView
import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.service.impl.ShoppingCartService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/shopping-cart")
class ShoppingCartController(
    val shoppingCartService: ShoppingCartService
) {

    @PostMapping
    fun saveShoppingCart(@RequestBody @Valid shoppingCartDto: ShoppingCartDto): ResponseEntity<ShoppingCartView> {
        val savedShoppingCart: ShoppingCart = this.shoppingCartService.save(shoppingCartDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(ShoppingCartView(savedShoppingCart))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<ShoppingCartView> {
        val shoppingCart: ShoppingCart = this.shoppingCartService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(ShoppingCartView(shoppingCart))
    }

    @PatchMapping
    fun updateShoppingCart(
        @RequestParam(value = "shoppingCartId") id: Long,
        @RequestBody @Valid shoppingCartUpdateDto: ShoppingCartUpdateDto
    ): ResponseEntity<ShoppingCartView> {
        val shoppingCart: ShoppingCart = this.shoppingCartService.findById(id)
        val shoppingCartToUpdate: ShoppingCart = shoppingCartUpdateDto.toEntity(shoppingCart)
        val shoppingCartUpdated: ShoppingCart = this.shoppingCartService.save(shoppingCart)
        return ResponseEntity.status(HttpStatus.OK).body(ShoppingCartView(shoppingCartUpdated))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteShoppingCart(@PathVariable id: Long) = this.shoppingCartService.delete(id)

}