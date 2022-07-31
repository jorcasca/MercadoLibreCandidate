package com.jorgecastanov.mercadolibrecandidate

import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductDataSource
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.data.repository.ProductRepository
import com.jorgecastanov.mercadolibrecandidate.data.repository.ProductRepositoryImpl
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    // SUT
    private lateinit var ddpRepositoryImpl: ProductRepository

    // Collaborators
    private val productDataSource: ProductDataSource = mockk()

    // Mockk
    private val products = listOf(Product(id = "a"), Product(id = "b"))
    private val keyWord = "tv"

    @Before
    fun init() {
        clearMocks(productDataSource)
        ddpRepositoryImpl = ProductRepositoryImpl(productDataSource)
    }

    @Test
    fun `When it tries to getProducts, Then it remote data source gets called`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns emptyList()
        // When
        runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        coVerify { productDataSource.getProducts(any()) }
    }

    @Test
    fun `When it tries to getProducts, Then two products are retrieved`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns products
        // When
        val prods = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        coVerify { productDataSource.getProducts(any()) }
        assertEquals(products, prods)
    }
}
