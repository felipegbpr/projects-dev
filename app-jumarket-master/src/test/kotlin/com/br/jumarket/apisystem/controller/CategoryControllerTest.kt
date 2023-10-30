package com.br.jumarket.apisystem.controller

import com.br.jumarket.apisystem.dto.request.CategoryDto
import com.br.jumarket.apisystem.dto.request.CategoryUpdateDto
import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.repository.CategoryRespository
import com.fasterxml.jackson.databind.ObjectMapper
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
class CategoryControllerTest {
    @Autowired
    private lateinit var categoryRepository: CategoryRespository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/categories"
    }

    @BeforeEach
    fun setup() = categoryRepository.deleteAll()

    @BeforeEach
    fun tearDown() = categoryRepository.deleteAll()

    @Test
    fun `should create a category and return 201 status`() {
        //given
        val categoryDto: CategoryDto = builderCategoryDto()
        val valueAsString: String = objectMapper.writeValueAsString(categoryDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Produtos de Limpeza"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(13))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should find category by id and return 200 status`() {
        //given
        val category: Category = categoryRepository.save(builderCategoryDto().toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${category.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Produtos de Limpeza"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should update category by id and return 200 status`() {
        //given
        val category: Category = categoryRepository.save(builderCategoryDto().toEntity())
        val categoryUpdateDto: CategoryUpdateDto = builderCategoryUpdateDto()
        val valueAsString: String = objectMapper.writeValueAsString(categoryUpdateDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?categoryId=${category.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(
                "Produtos de Limpeza Updated"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `should delete category by id and return 204 status`() {
        //given
        val category: Category = categoryRepository.save(builderCategoryDto().toEntity())
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${category.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent())
            .andDo(MockMvcResultHandlers.print())
    }

    private fun builderCategoryDto(
        name: String = "Produtos de Limpeza"
    ) = CategoryDto(
        name = name
    )

    private fun builderCategoryUpdateDto(
        name: String = "Produtos de Limpeza Updated"
    ): CategoryUpdateDto = CategoryUpdateDto(
        name = name
    )

}