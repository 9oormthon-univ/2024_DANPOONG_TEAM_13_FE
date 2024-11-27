package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class PostDetailResponse(
    @SerializedName("writer_info")
    val writeInfo: PostDetailWriterInfo,
    @SerializedName("board_info")
    val boardInfo: PostDetailBoardInfo
)

data class PostDetailWriterInfo(
    @SerializedName("profile_img_url")
    val profileImgUrl: String,
    @SerializedName("masked_nickname")
    val maskedNickname: String,
    @SerializedName("is_me")
    val isMe: Boolean
)

data class PostDetailBoardInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("posted_ago")
    val postedAgo: String,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("is_liked")
    val isLiked: Boolean
)