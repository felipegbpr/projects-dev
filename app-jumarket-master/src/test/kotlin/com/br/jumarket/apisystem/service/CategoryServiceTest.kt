package com.br.jumarket.apisystem.service

import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.CategoryRespository
import com.br.jumarket.apisystem.service.impl.CategoryService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class CategoryServiceTest {

    @MockK lateinit var categoryRepository: CategoryRespository
    @InjectMockKs lateinit var categoryService: CategoryService

    @Test
    fun `should create category`() {
        //given
        val fakeCategory: Category = buildCategory()
        every { categoryRepository.save(any()) } returns fakeCategory
        //when
        val actual: Category = categoryService.save(fakeCategory)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCategory)
        verify(exactly = 1) { categoryRepository.save(fakeCategory) }
    }

    @Test
    fun `should find category by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCategory: Category = buildCategory(id = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        //when
        val actual: Category = categoryService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Category::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCategory)
        verify( exactly = 1 ) { categoryRepository.findById(fakeId) }
    }

    @Test
    fun `should not find category by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { categoryRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { categoryService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { categoryRepository.findById(fakeId) }

    }

    @Test
    fun `should delete category by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCategory: Category = buildCategory(id = fakeId)
        every { categoryRepository.findById(fakeId) } returns Optional.of(fakeCategory)
        every { categoryRepository.delete(fakeCategory) } just runs
        //when
        categoryService.delete(fakeId)
        //then
        verify(exactly = 1) { categoryRepository.findById(fakeId) }
        verify(exactly = 1) { categoryRepository.delete(fakeCategory) }
    }

    companion object {
        fun buildCategory(
            name: String = "Produtos de Limpeza",
            id: Long = 1L
        ) = Category (
            name = name,
            id = id
        )
    }

}