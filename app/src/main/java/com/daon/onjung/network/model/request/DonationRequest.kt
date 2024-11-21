package com.daon.onjung.network.model.request

import com.google.gson.annotations.SerializedName

data class DonationRequest(
    @SerializedName("event_id")
    val eventId: Int,
    @SerializedName("donation_amount")
    val donationAmount: Int
)
