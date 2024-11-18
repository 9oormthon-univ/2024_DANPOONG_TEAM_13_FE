package com.daon.onjung.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Int): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
    return "${numberFormat.format(value)} 원"
}

fun formatCurrencyInTenThousandUnit(value: Int): String {
    return if (value >= 10_000) {
        val tenThousandUnit = value / 10_000
        "$tenThousandUnit 만원"
    } else {
        formatCurrency(value)
    }
}
