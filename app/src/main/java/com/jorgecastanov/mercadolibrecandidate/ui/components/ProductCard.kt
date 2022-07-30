package com.jorgecastanov.mercadolibrecandidate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme

@Composable
fun ProductCard(
    title: String,
    price: Double,
    available_quantity: Int,
    condition: String,
    thumbnail: String,
    onTab: () -> Unit
) {
    MercadoLibreCandidateTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onTab() },
            elevation = 10.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp),
                        model = thumbnail,
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h1
                        )
                        Text(
                            "$ $price",
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            "$available_quantity Available",
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            condition,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    MercadoLibreCandidateTheme {
        ProductCard(
            title = "Samsung Galaxy J4+ Dual Sim 32 Gb Negro (2 Gb Ram)",
            price = 19609.0,
            condition = "new",
            available_quantity = 1,
            thumbnail = "http://mla-s1-p.mlstatic.com/943469-MLA31002769183_062019-I.jpg",
            onTab = {}
        )
    }
}