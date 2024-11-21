package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.StoreCategory
import com.google.gson.annotations.SerializedName

data class MealTicketListResponse(
    @SerializedName("ticket_list")
    val ticketList: List<MealTicketStoreResponse>,
    @SerializedName("has_next")
    val hasNext: Boolean
)

data class MealTicketStoreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("store_info")
    val storeInfo: MealTicketStoreInfo,
    @SerializedName("expiration_date")
    val expirationDate: String,
    @SerializedName("ticket_price")
    val ticketPrice: Int,
    @SerializedName("is_validate")
    val isValidate: Boolean
)

data class MealTicketStoreInfo(
    @SerializedName("logo_img_url")
    val logoImgUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: StoreCategory,
    @SerializedName("address")
    val address: String
)

