package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.network.model.StoreTag
import com.google.gson.annotations.SerializedName

data class StoreDetailResponse(
    @SerializedName("store_info")
    val storeInfo: StoreDetailInfo,
    @SerializedName("event_info")
    val eventInfo: EventDetailInfo,
    @SerializedName("onjung_info")
    val onjungInfo: OnjungDetailInfo,
    @SerializedName("store_histories")
    val storeHistories: List<StoreHistory>
)

data class StoreDetailInfo(
    @SerializedName("banner_img_url")
    val bannerImgUrl: String,
    @SerializedName("tag")
    val tags: List<StoreTag>,
    @SerializedName("title")
    val title: String,
    @SerializedName("youtube_url")
    val youtubeUrl: String,
    @SerializedName("logo_img_url")
    val logoImgUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: StoreCategory,
    @SerializedName("address")
    val address: String,
    @SerializedName("introduction")
    val introduction: String
)

data class EventDetailInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("total_amount")
    val totalAmount: Int,
    @SerializedName("rest_of_date")
    val restOfDate: Int
)

data class OnjungDetailInfo(
    @SerializedName("total_donator_count")
    val totalDonatorCount: Int,
    @SerializedName("total_donation_amount")
    val totalDonationAmount: Int,
    @SerializedName("total_receipt_amount")
    val totalReceiptAmount: Int,
    @SerializedName("total_shared_amount")
    val totalSharedAmount: Int
)

data class StoreHistory(
    @SerializedName("date")
    val date: String,
    @SerializedName("info")
    val info: List<StoreHistoryContent>,
)

data class StoreHistoryContent(
    @SerializedName("content")
    val content: String,
    @SerializedName("amount")
    val amount: String
)