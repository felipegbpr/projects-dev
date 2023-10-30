package com.br.jumarket.apisystem.exceptions

data class BusinessException(override val message: String?) : RuntimeException(message)