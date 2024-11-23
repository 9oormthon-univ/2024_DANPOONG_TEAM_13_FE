package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.StoreTag
import com.google.gson.annotations.SerializedName

data class DonationResponse(
    @SerializedName("donation_amount")
    val donationAmount: Int,
    @SerializedName("issue_date")
    val issueDate: String,
    @SerializedName("store_info")
    val storeInfo: DonationStoreInfo
)

data class DonationStoreInfo(
    @SerializedName("logo_img_url")
    val logoImgUrl: String,
    @SerializedName("tag")
    val tag: List<StoreTag>,
    @SerializedName("name")
    val name: String
)
