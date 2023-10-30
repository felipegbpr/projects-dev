package com.br.jumarket.apisystem.repository

import com.br.jumarket.apisystem.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRespository: JpaRepository<Category, Long>