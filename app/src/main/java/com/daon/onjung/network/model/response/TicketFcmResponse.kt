package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.StoreCategory

data class TicketFcmResponse(
    val store_name: String,
    val address: String,
    val category: StoreCategory,
    val user_name: String,
    val expiration_date: String,
    val logo_img_url: String
)
