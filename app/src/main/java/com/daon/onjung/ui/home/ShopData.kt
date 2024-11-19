package com.daon.onjung.ui.home

import androidx.compose.ui.graphics.Color

data class ShopData(
    val shopId: Int,
    val tag: String,
    val tagColor: Color,
    val title: String,
    val likeCount: String,
    val name: String,
    val region: String
)