package com.br.jumarket.apisystem.service


import com.br.jumarket.apisystem.entity.Product
import com.br.jumarket.apisystem.entity.ShoppingCart
import com.br.jumarket.apisystem.exceptions.BusinessException
import com.br.jumarket.apisystem.repository.ShoppingCartRepository
import com.br.jumarket.apisystem.service.impl.ShoppingCartService
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
import java.util.Optional
import java.util.Random

@ExtendWith(MockKExtension::class)
//@ActiveProfiles("test")
class ShoppingCartServiceTest {
    @MockK lateinit var shoppingCartRepository: ShoppingCartRepository
    @InjectMockKs lateinit var shoppingCartService: ShoppingCartService

    @Test
    fun `should create shopping cart`() {
        //given
        val fakeShoppingCart: ShoppingCart = buildShoppingCart()
        every { shoppingCartRepository.save(any()) } returns fakeShoppingCart
        //when
        val actual: ShoppingCart = shoppingCartService.save(fakeShoppingCart)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeShoppingCart)
        verify(exactly = 1) { shoppingCartRepository.save(fakeShoppingCart) }
    }

    @Test
    fun `should find shopping cart by id` () {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeShoppingCart: ShoppingCart = buildShoppingCart(id = fakeId)
        every { shoppingCartRepository.findById(fakeId) } returns Optional.of(fakeShoppingCart)
        //when
        val actual: ShoppingCart = shoppingCartService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(ShoppingCart::class.java)
        Assertions.assertThat(actual).isSameAs(fakeShoppingCart)
        verify ( exactly = 1 ) { shoppingCartRepository.findById(fakeId) }
    }

    @Test
    fun `should not find shopping cart by invalid and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { shoppingCartRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { shoppingCartService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { shoppingCartRepository.findById(fakeId) }
    }

    @Test
    fun `should delete shopping cart by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeShoppingCart: ShoppingCart = buildShoppingCart(id = fakeId)
        every { shoppingCartRepository.findById(fakeId) } returns Optional.of(fakeShoppingCart)
        every { shoppingCartRepository.delete(fakeShoppingCart) } just runs
        //when
        shoppingCartService.delete(fakeId)
        //then
        verify(exactly = 1) { shoppingCartRepository.findById(fakeId) }
        verify(exactly = 1) { shoppingCartRepository.delete(fakeShoppingCart) }
    }

    companion object {
        fun buildShoppingCart(
            product: Product? = Product(
                id = 3,
                nameProduct = "Balas de Morango",
                unitMeasurement = "g",
                unitPrice = 5.4F,
                category = null
            ),
            quantityItems: Int = 2,
            salePrice: Float = 20.3F,
            paymentType: String = "PIX",
            id: Long = 2L
        ) = ShoppingCart(
            product = product,
            quantityItems = quantityItems,
            salePrice = salePrice,
            paymentType = paymentType,
            id = id
        )
    }
}