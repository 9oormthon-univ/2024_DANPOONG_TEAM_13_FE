package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.OnjungType
import com.google.gson.annotations.SerializedName

data class SharedRestaurantListResponse(
    @SerializedName("store_list")
    val storeList: List<SharedRestaurantResponse>,
)

data class SharedRestaurantResponse(
    @SerializedName("onjung_type")
    val onjungType: OnjungType,
    @SerializedName("store_name")
    val storeName: String,
    @SerializedName("store_title")
    val storeTitle: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("date")
    val date: String
)
