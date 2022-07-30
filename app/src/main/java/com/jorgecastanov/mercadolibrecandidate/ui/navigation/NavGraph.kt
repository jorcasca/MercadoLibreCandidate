package com.jorgecastanov.mercadolibrecandidate.ui.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.ui.screens.detail.DetailScreen
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedScreen
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    MercadoLibreCandidateTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "feed"
            ) {
                composable(route = "feed") {
                    FeedScreen(navController)
                }
                composable(route = "detail") {
                    val product =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
                    product?.let { DetailScreen(navController, product) }
                }
            }
        }
    }
}
