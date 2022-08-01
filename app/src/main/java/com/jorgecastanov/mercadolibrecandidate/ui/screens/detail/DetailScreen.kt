package com.jorgecastanov.mercadolibrecandidate.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jorgecastanov.mercadolibrecandidate.R
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import com.jorgecastanov.mercadolibrecandidate.ui.components.NavigationAppBar
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme
import com.jorgecastanov.mercadolibrecandidate.ui.utils.CurrencyHelper.convertCurrency
import com.jorgecastanov.mercadolibrecandidate.ui.utils.ShareHelper

@Composable
fun DetailScreen(navController: NavController, product: Product) {
    Scaffold(
        topBar = {
            NavigationAppBar(
                navigationIcon = Icons.Filled.ArrowBack,
                navigationAction = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            MercadoLibreCandidateTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    RenderDetail(product)
                }
            }
        }
    )
}

@Composable
fun RenderDetail(product: Product) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.h3
            )
            AsyncImage(
                modifier = Modifier.size(400.dp),
                model = product.thumbnail,
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.available, product.price.convertCurrency()),
                style = MaterialTheme.typography.h1
            )
            Text(
                text = stringResource(id = R.string.available, product.available_quantity),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = product.condition,
                style = MaterialTheme.typography.body1
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.reference, product.id),
                style = MaterialTheme.typography.body1
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                onClick = {
                    ShareHelper.onSharePressed(context, product.permalink)
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.share),
                        style = MaterialTheme.typography.body1
                    )
                })
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    uriHandler.openUri(product.permalink)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ),
                content = {
                    Text(
                        text = stringResource(id = R.string.buy),
                        style = MaterialTheme.typography.body1
                    )
                })
        }
    }
}
