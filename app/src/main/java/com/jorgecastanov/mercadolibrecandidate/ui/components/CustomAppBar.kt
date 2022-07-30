package com.jorgecastanov.mercadolibrecandidate.ui.components

import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.jorgecastanov.mercadolibrecandidate.ui.theme.MercadoLibreCandidateTheme

typealias NavigationAction = () -> Unit

@Composable
fun CustomAppBar(title: String? = null,
                 navigationIcon: ImageVector? = null,
                 navigationAction: NavigationAction? = null) {
    val titleText = title ?: ""
    if(navigationIcon != null && navigationAction != null) {
        TopAppBar(
            title = {Text(titleText)},
            navigationIcon = {
                IconButton(onClick = {
                    navigationAction()
                }) {
                    Icon(navigationIcon, "")
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        )
    } else {
        TopAppBar(
            title = {Text(titleText)},
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomAppBarPreview() {
    MercadoLibreCandidateTheme {
        CustomAppBar("Mercado Libre Candidate")
    }
}