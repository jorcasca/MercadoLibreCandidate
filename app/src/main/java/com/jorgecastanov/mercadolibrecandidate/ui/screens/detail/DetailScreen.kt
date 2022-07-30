package com.jorgecastanov.mercadolibrecandidate.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jorgecastanov.mercadolibrecandidate.ui.components.CustomAppBar

@Composable
fun DetailScreen(navController: NavController, productId: String) {

    Scaffold(
        topBar = {
            CustomAppBar(navigationIcon = Icons.Filled.ArrowBack) {
                navController.navigate("feed") {
                    popUpTo("feed")
                }
            }
        },
        content = {
            Column {
                Text(
                    productId,
                    style = MaterialTheme.typography.h3
                )
                Button(onClick = {
                    navController.navigate("feed") {
                        popUpTo("feed")
                    }
                }) {
                    Text(text = "Back")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    DetailScreen(navController, "Product Detail")
}