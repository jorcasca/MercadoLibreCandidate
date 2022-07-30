package com.jorgecastanov.mercadolibrecandidate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jorgecastanov.mercadolibrecandidate.ui.components.ProductCard
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme
import androidx.compose.foundation.lazy.items

@Composable
fun FeedScreen(navController: NavController) {
    val list = listOf("Product A", "Product B", "Product C", "Product D")

    MercadoLibreCandidateTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) { product ->
                    ProductCard(
                        title = product,
                        price = 19609,
                        condition = "new",
                        available_quantity = 1,
                        permalink = "https://www.mercadolibre.com.ar/p/MLA13550363",
                        thumbnail = "https://picsum.photos/200",
                    ) {
                        navController.navigate("detail/${product}") {
                            launchSingleTop = true
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    val navController = rememberNavController()
    FeedScreen(navController)
}