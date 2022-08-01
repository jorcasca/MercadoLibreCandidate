package com.jorgecastanov.mercadolibrecandidate

import com.jorgecastanov.mercadolibrecandidate.data.api.ProductApi
import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductDataSource
import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductRemoteDataSource
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.data.model.Search
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ProductRemoteDataSourceTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    // SUT
    private lateinit var productDataSource: ProductDataSource

    // Collaborators
    private val productApi: ProductApi = mockk()

    // mockk
    private val products = listOf(Product(id = "a"), Product(id = "b"))
    private val search = Search(site_id = "a", results = products)
    private val keyWord = "tv"

    @Before
    fun init() {
        clearMocks(productApi)
        productDataSource = ProductRemoteDataSource(productApi)
    }

    @Test
    fun `When it tries to getProducts, Then it remote api gets called`() {
        val response = Response.success(search)

        // Given
        coEvery { productApi.getProducts(any()) } returns response
        // When
        runBlocking { productDataSource.getProducts(keyWord) }
        // Then
        coVerify { productApi.getProducts(any()) }
    }

    @Test
    fun `When it tries to getProducts, Then two products are retrieved`() {
        val response = Response.success(search)

        // Given
        coEvery { productApi.getProducts(any()) } returns response
        // When
        val srch = runBlocking { productDataSource.getProducts(keyWord) }
        // Then
        coVerify { productApi.getProducts(any()) }
        Assert.assertEquals(products, srch.getOrNull())
    }
}
