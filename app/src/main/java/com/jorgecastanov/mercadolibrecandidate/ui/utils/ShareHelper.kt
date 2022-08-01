package com.jorgecastanov.mercadolibrecandidate.ui.utils

import android.content.Context
import android.content.Intent

private const val SHARE_FORMAT = "text/plain"

object ShareHelper {

    fun onSharePressed(context: Context, content: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = SHARE_FORMAT
        }
        val shareIntent = Intent.createChooser(sendIntent, null).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(shareIntent)
    }

}
