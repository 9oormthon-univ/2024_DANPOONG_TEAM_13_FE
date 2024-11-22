package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class NotificationAllowedResponse(
    @SerializedName("notification_allowed")
    val notificationAllowed: Boolean
)