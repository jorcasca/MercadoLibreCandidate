package com.jorgecastanov.mercadolibrecandidate.ui.utils

import java.text.DecimalFormat

object CurrencyHelper {

    fun Double.convertCurrency(): String {
        val formatter = DecimalFormat("###,###,###")
        return formatter.format(this).replace(',', '.')
    }

}
