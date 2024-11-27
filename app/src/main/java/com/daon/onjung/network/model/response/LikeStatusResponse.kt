package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class LikeStatusResponse(
    @SerializedName("is_like")
    val isLike: Boolean
)
