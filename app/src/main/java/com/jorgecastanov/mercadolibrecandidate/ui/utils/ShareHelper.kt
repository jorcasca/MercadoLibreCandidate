package com.jorgecastanov.mercadolibrecandidate.ui.utils

import android.content.Context
import android.content.Intent

object ShareHelper {

    fun onSharePressed(context: Context, content: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

}
