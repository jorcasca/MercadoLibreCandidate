package com.jorgecastanov.mercadolibrecandidate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgecastanov.mercadolibrecandidate.ui.screens.detail.DetailScreen
import com.jorgecastanov.mercadolibrecandidate.ui.screens.feed.FeedScreen
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NavigationHost() }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    MercadoLibreCandidateTheme {
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "feed") {
                composable(route = "feed") {
                    FeedScreen(navController)
                }
                composable(route = "detail/{productId}") { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString("productId") ?: ""
                    DetailScreen(navController, productId)
                }
            }
        }
    }
}