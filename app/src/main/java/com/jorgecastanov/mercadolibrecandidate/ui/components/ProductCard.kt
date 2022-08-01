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
import com.jorgecastanov.mercadolibrecandidate.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme
import com.jorgecastanov.mercadolibrecandidate.ui.utils.CurrencyHelper.convertCurrency

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
                            style = MaterialTheme.typography.h3
                        )
                        Text(
                            "$ ${price.convertCurrency()}",
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            stringResource(R.string.available, available_quantity),
                            style = MaterialTheme.typography.body2
                        )
                        Text(
                            condition,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}
