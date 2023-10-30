package com.br.jumarket.apisystem.entity

import com.br.jumarket.apisystem.enumeration.PaymentType
import jakarta.persistence.*
import lombok.Data
import kotlin.jvm.Transient

@Entity
@Data
@Table(name = "shopping_cart")
data class ShoppingCart(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column @Transient var product: Product? = null,
    @Column(nullable = false) var quantityItems: Int,
    @Column(nullable = false) var salePrice: Float,
    @Column val paymentType: String = PaymentType.PIX.toString()
)
