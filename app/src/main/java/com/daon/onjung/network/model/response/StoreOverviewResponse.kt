package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.StoreTag
import com.google.gson.annotations.SerializedName

data class StoreOverviewResponse(
    @SerializedName("store_list")
    val storeList: List<StoreOverviewInfo>,
    @SerializedName("has_next")
    val hasNext: Boolean
)

data class StoreOverviewInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tag")
    val tag: StoreTag,
    @SerializedName("title")
    val title: String,
    @SerializedName("banner_img_url")
    val bannerImgUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("total_onjung_count")
    val totalOnjungCount: Int
)

