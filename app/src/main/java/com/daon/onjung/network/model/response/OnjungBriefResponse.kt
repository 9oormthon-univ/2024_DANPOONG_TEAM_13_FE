package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class OnjungBriefResponse(
    @SerializedName("total_onjung_count")
    val totalOnjungCount: Int,
    @SerializedName("total_onjung_amount")
    val totalOnjungAmount: Int,
)
