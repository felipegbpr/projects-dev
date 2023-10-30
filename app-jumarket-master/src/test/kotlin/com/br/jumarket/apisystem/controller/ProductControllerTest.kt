package com.br.jumarket.apisystem.controller

import com.br.jumarket.apisystem.dto.request.ProductDto
import com.br.jumarket.apisystem.dto.request.ProductUpdateDto
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.repository.ProductRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class ProductControllerTest {
    @Autowired
    private lateinit var productRepository: ProductRepository


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/products"
    }

    @BeforeEach
    fun setup() = productRepository.deleteAll()

    @AfterEach
    fun tearDown() = productRepository.deleteAll()

    @Test
    fun `should create a product and return 201 status`() {
        //given
        val productDto: ProductDto = builderProductDto()
        val valueAsString: String = objectMapper.writeValueAsString(productDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nameProduct").value("Sabão em Pó"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitMeasurement").value("KG"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value("1.5"))
            // .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(19))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find product by id and return 200 status`() {
        //given
        val product: Product = productRepository.save(builderProductDto().toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${product.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nameProduct").value("Sabão em Pó"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitMeasurement").value("KG"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value("1.5"))
            // .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(19))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update a product and return 200 status`() {
        //given
        val product: Product = productRepository.save(builderProductDto().toEntity())
        val productUpdateDto: ProductUpdateDto = builderProductUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(productUpdateDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?productId=${product.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nameProduct").value("Sabão em Pó Update"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitMeasurement").value("KG Update"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.unitPrice").value("2.5"))
            // .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(19))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should delete product by id and return 204 status`() {
        //given
        val product: Product = productRepository.save(builderProductDto().toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${product.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
    }


    private fun builderProductDto(
        nameProduct: String = "Sabão em Pó",
        unitMeasurement: String = "KG",
        unitPrice: Float = 1.5F,
        categoryId: Long = 19L
    ) = ProductDto (
        nameProduct = nameProduct,
        unitMeasurement = unitMeasurement,
        unitPrice = unitPrice,
        categoryId = categoryId
    )

    private fun builderProductUpdateDto(
        nameProduct: String = "Sabão em Pó Update",
        unitMeasurement: String = "KG Update",
        unitPrice: Float = 2.5F,
    ) = ProductUpdateDto (
        nameProduct = nameProduct,
        unitMeasurement = unitMeasurement,
        unitPrice = unitPrice,
    )

}