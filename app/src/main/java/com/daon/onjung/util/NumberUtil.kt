package com.daon.onjung.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Int): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.KOREA)
    return "${numberFormat.format(value)} 원"
}
