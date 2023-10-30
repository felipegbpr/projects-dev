package com.br.jumarket.apisystem.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("springjumarketapplicationsystem-public")
            .pathsToMatch("/api/categories/**", "/api/products/**", "/api/shopping-cart/**")
            .build()
    }
}