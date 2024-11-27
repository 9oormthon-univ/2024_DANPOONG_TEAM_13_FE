package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class BoardListResponse (
    @SerializedName("board_list")
    val boardList: List<BoardDetail>,
    @SerializedName("has_next")
    val hasNext: Boolean
)

data class BoardDetail (
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("title_summary")
    val titleSummary: String,
    @SerializedName("content_summary")
    val contentSummary: String,
    @SerializedName("posted_ago")
    val postedAgo: String,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("comment_count")
    val commentCount: Int
)