package com.jorgecastanov.mercadolibrecandidate.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.ui.screens.detail.DetailScreen
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedScreen
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme
import com.jorgecastanov.mercadolibrecandidate.ui.navigation.Navigation.FEED_SCREEN
import com.jorgecastanov.mercadolibrecandidate.ui.navigation.Navigation.DETAIL_SCREEN
import com.jorgecastanov.mercadolibrecandidate.ui.navigation.Navigation.PRODUCT

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    MercadoLibreCandidateTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = FEED_SCREEN
            ) {
                composable(route = FEED_SCREEN) {
                    FeedScreen(navController)
                }
                composable(route = DETAIL_SCREEN) {
                    val product =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Product>(PRODUCT)
                    product?.let { DetailScreen(navController, product) }
                }
            }
        }
    }
}

fun goProductDetail(navController: NavController, product: Product){
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = PRODUCT,
        value = product
    )
    navController.navigate(DETAIL_SCREEN)
}
