package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class OcrResponse(
    @SerializedName("store_name")
    val storeName: String,
    @SerializedName("store_address")
    val storeAddress: String,
    @SerializedName("payment_date")
    val paymentDate: String,
    @SerializedName("payment_amount")
    val paymentAmount: String
)
