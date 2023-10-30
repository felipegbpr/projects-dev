package com.br.jumarket.apisystem.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(nullable = false) var nameProduct: String = "",
    @Column(nullable = false) var unitMeasurement: String = "",
    @Column(nullable = false) var unitPrice: Float = 0F,
    @ManyToOne var category: Category? = null
)
