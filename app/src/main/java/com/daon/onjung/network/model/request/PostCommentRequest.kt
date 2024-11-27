package com.daon.onjung.network.model.request

import com.google.gson.annotations.SerializedName

data class PostCommentRequest(
    @SerializedName("content")
    val content: String
)
