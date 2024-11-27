package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class CommentListResponse(
    @SerializedName("comment_list")
    val commentList: List<CommentDetail>,
    @SerializedName("has_next")
    val hasNext: Boolean
)

data class CommentDetail(
    @SerializedName("writer_info")
    val writerInfo: CommentWriterInfo,
    @SerializedName("comment_info")
    val commentInfo: CommentInfo
)

data class CommentWriterInfo(
    @SerializedName("profile_img_url")
    val profileImgUrl: String,
    @SerializedName("masked_nickname")
    val maskedNickname: String,
    @SerializedName("is_me")
    val isMe: Boolean
)

data class CommentInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("posted_ago")
    val postedAgo: String,
    @SerializedName("content")
    val content: String
)

