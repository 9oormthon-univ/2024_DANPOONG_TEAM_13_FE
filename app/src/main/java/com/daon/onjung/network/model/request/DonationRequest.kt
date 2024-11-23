package com.daon.onjung.network.model.request

import com.google.gson.annotations.SerializedName

data class DonationRequest(
    @SerializedName("donation_amount")
    val donationAmount: Int
)
