package com.jorgecastanov.mercadolibrecandidate.ui.utils

import java.text.DecimalFormat

private const val CURRENCY_FORMAT = "###,###,###"
private const val US_DOT = ","
private const val COL_DOT = "."

object CurrencyHelper {

    fun Double.convertCurrency(): String {
        val formatter = DecimalFormat(CURRENCY_FORMAT)
        return formatter.format(this).replace(US_DOT, COL_DOT)
    }

}
