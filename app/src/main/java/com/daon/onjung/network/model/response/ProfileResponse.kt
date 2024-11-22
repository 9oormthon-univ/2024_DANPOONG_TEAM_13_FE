package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("profile_img_url")
    val profileImgUrl: String,
    @SerializedName("notification_allowed")
    val notificationAllowed: Boolean
)
