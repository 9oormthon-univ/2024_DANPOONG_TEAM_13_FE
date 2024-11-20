package com.daon.onjung.network.model.request

import com.daon.onjung.network.model.LoginProvider
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("provider")
    val provider: LoginProvider
)

