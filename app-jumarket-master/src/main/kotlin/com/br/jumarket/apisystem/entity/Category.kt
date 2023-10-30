package com.br.jumarket.apisystem.entity

import jakarta.persistence.*
import lombok.Data
import kotlin.jvm.Transient

@Entity
@Data
@Table(name = "categories")
data class Category (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(nullable = false) var name: String = "",
    @Column(nullable  = false, ) @Transient @OneToMany(fetch = FetchType.LAZY,
        cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST),
        mappedBy = "category")
    var products: List<Product> = mutableListOf(),
)