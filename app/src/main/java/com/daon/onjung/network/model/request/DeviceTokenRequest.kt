package com.daon.onjung.network.model.request

import com.google.gson.annotations.SerializedName

data class DeviceTokenRequest(
    @SerializedName("device_token")
    val deviceToken: String
)
