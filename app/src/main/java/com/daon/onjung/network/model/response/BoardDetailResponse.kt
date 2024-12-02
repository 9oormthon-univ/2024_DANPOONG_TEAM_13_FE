package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class BoardDetailResponse(
    @SerializedName("writer_info")
    val writerInfo: BoardDetailWriterInfo,
    @SerializedName("board_info")
    val boardInfo: BoardDetailBoardInfo
)

data class BoardDetailWriterInfo(
    @SerializedName("profile_img_url")
    val profileImgUrl: String,
    @SerializedName("masked_nickname")
    val maskedNickname: String,
    @SerializedName("is_me")
    val isMe: Boolean
)

data class BoardDetailBoardInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_url")
    val imgUrl: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("posted_ago")
    val postedAgo: String,
    @SerializedName("goal_count")
    val goalCount: Int,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("remaining_days")
    val remainingDays: Int
)