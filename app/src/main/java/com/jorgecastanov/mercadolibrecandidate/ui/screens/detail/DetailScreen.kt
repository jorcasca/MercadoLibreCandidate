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
import com.jorgecastanov.mercadolibrecandidate.ui.components.NavigationAppBar

@Composable
fun DetailScreen(navController: NavController, productId: String) {

    Scaffold(
        topBar = {
            NavigationAppBar(
                navigationIcon = Icons.Filled.ArrowBack,
                navigationAction = {
                    navController.navigate("feed") {
                        popUpTo("feed")
                    }
                }
            )
        },
        content = {
            Column {
                Text(
                    text = productId,
                    style = MaterialTheme.typography.h3
                )
                Button(
                    onClick = {
                        navController.navigate("feed") {
                            popUpTo("feed")
                        }
                    },
                    content = {
                        Text(
                            text = "Back"
                        )
                    })
            }
        }
    )
}
