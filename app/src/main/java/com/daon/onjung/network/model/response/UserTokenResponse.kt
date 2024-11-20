package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class UserTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)
