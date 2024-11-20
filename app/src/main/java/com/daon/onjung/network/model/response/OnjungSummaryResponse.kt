package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class OnjungSummaryResponse(
    @SerializedName("date_time")
    val dateTime: String,
    @SerializedName("total_donation_count")
    val totalDonationCount: Int,
    @SerializedName("total_donator_count")
    val totalDonatorCount: Int
)
