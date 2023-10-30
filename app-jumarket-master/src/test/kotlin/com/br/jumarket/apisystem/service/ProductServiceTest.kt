package com.br.jumarket.apisystem.service

import com.br.jumarket.apisystem.entity.Category
import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.ProductRepository
import com.br.jumarket.apisystem.service.impl.ProductService
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
import org.springframework.test.context.ActiveProfiles
import java.util.Optional
import java.util.Random

@ExtendWith(MockKExtension::class)
//@ActiveProfiles("test")
class ProductServiceTest {
    @MockK lateinit var productRepository: ProductRepository
    @InjectMockKs lateinit var productService: ProductService

    @Test
    fun `should create product`() {
        //given
        val fakeProduct: Product = buildProduct()
        every { productRepository.save(any()) } returns fakeProduct
        //when
        val actual: Product = productService.save(fakeProduct)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeProduct)
        verify(exactly = 1) { productRepository.save(fakeProduct) }
    }

    @Test
    fun `should find product by id` () {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeProduct: Product = buildProduct(id = fakeId)
        every { productRepository.findById(fakeId) } returns Optional.of(fakeProduct)
        //when
        val actual: Product = productService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Product::class.java)
        Assertions.assertThat(actual).isSameAs(fakeProduct)
        verify ( exactly = 1 ) { productRepository.findById(fakeId) }
    }

    @Test
    fun `should not find product by invalid and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { productRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { productService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { productRepository.findById(fakeId) }
    }

    @Test
    fun `should delete product by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeProduct: Product = buildProduct(id = fakeId)
        every { productRepository.findById(fakeId) } returns Optional.of(fakeProduct)
        every { productRepository.delete(fakeProduct) } just runs
        //when
        productService.delete(fakeId)
        //then
        verify(exactly = 1) { productRepository.findById(fakeId) }
        verify(exactly = 1) { productRepository.delete(fakeProduct) }
    }

    companion object {
        fun buildProduct(
            nameProduct: String = "Produtos de Limpeza",
            unitMeasurement: String = "KG",
            unitPrice: Float = 1.3F,
            categoryId: Category? = null,
            id: Long = 1L
        ) = Product(
            nameProduct = nameProduct,
            unitMeasurement = unitMeasurement,
            unitPrice = unitPrice,
            category = categoryId,
            id = id
        )
    }
}