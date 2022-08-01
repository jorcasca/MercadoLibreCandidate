package com.jorgecastanov.mercadolibrecandidate

import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.ServiceUnavailableException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.InternalServerException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.InvalidRequestException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.UnauthorizedTokenException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.ScopeException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException.NotFoundException
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
import org.junit.Assert
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
        val result = Result.success(emptyList<Product>())

        // Given
        coEvery { productDataSource.getProducts(any()) } returns result
        // When
        runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        coVerify { productDataSource.getProducts(any()) }
    }

    @Test
    fun `When it tries to getProducts, Then two products are retrieved`() {
        val result = Result.success(products)

        // Given
        coEvery { productDataSource.getProducts(any()) } returns result
        // When
        val prods = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        coVerify { productDataSource.getProducts(any()) }
        assertEquals(products, prods.getOrNull())
    }

    @Test
    fun `When it tries to getProducts, Then ServiceUnavailableException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(ServiceUnavailableException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is ServiceUnavailableException)
    }

    @Test
    fun `When it tries to getProducts, Then InternalServerException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(InternalServerException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is InternalServerException)
    }

    @Test
    fun `When it tries to getProducts, Then NotFoundException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(NotFoundException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is NotFoundException)
    }

    @Test
    fun `When it tries to getProducts, Then InvalidRequestException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(InvalidRequestException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is InvalidRequestException)
    }


    @Test
    fun `When it tries to getProducts, Then UnauthorizedTokenException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(UnauthorizedTokenException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is UnauthorizedTokenException)
    }


    @Test
    fun `When it tries to getProducts, Then ScopeException`() {
        // Given
        coEvery { productDataSource.getProducts(any()) } returns Result.failure(ScopeException())
        // When
        val result = runBlocking { ddpRepositoryImpl.getProducts(keyWord) }
        // Then
        Assert.assertTrue(result.exceptionOrNull() is ScopeException)
    }
}
