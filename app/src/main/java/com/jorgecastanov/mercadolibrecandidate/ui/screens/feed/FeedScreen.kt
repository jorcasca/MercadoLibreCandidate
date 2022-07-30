package com.jorgecastanov.mercadolibrecandidate.ui.screens.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jorgecastanov.mercadolibrecandidate.ui.components.ProductCard
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jorgecastanov.mercadolibrecandidate.data.api.ApiHelper
import com.jorgecastanov.mercadolibrecandidate.data.api.ApiHelperImpl
import com.jorgecastanov.mercadolibrecandidate.data.api.RetrofitBuilder
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.data.repository.FeedRepository
import com.jorgecastanov.mercadolibrecandidate.data.repository.FeedRepositoryImpl
import com.jorgecastanov.mercadolibrecandidate.ui.components.FeedAppBar
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedState.Idle
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedState.Products
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedState.Error
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedState.Loading

@Composable
fun FeedScreen(navController: NavController) {

    val apiHelper: ApiHelper = ApiHelperImpl(RetrofitBuilder.apiService)
    val feedRepository: FeedRepository = FeedRepositoryImpl(apiHelper)
    val viewModel = FeedViewModel(feedRepository)
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            FeedAppBar(
                onSearchClicked = { search ->
                    viewModel.productIntent.trySend(FeedIntent.FetchProducts(search))
                }
            )
        },
        content = {
            MercadoLibreCandidateTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    RenderStates(
                        state = state,
                        navController = navController
                    )
                }
            }
        }
    )
}

@Composable
fun RenderStates(state: FeedState, navController: NavController) {
    when (state) {
        is Idle -> {
            OnEmptyProducts()
        }
        is Loading -> {
            OnLoadingProducts()
        }
        is Products -> {
            val products = (state).products
            OnLoadedProducts(products, navController)
        }
        is Error -> {
            val error = (state).error.toString()
            OnError(error)
        }
    }
}

@Composable
fun OnEmptyProducts() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Empty")
    }
}

@Composable
fun OnError(error: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(error)
    }
}

@Composable
fun OnLoadedProducts(products: List<Product>, navController: NavController) {
    if (products.isNotEmpty())
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    title = product.title,
                    price = product.price,
                    condition = product.condition,
                    available_quantity = product.available_quantity,
                    thumbnail = product.thumbnail,
                    onTab = {
                        navController.navigate("detail/${product.id}") {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    else
        OnEmptyProducts()
}

@Composable
fun OnLoadingProducts() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
