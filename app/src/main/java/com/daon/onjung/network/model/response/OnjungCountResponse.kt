package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class OnjungCountResponse(
    @SerializedName("total_onjung_count")
    val totalOnjungCount: Int
)
