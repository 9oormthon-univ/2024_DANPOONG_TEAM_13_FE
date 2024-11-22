package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class MyOnjungBriefResponse(
    @SerializedName("store_name_list")
    val storeNameList: List<String>,
    @SerializedName("remain_count")
    val remainCount: Int
)
