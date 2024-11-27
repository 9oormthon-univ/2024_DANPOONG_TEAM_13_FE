package com.daon.onjung.data.repository

import android.net.Uri
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.BoardDetailResponse
import com.daon.onjung.network.model.response.CommentListResponse
import com.daon.onjung.network.model.response.PostBoardResponse
import kotlinx.coroutines.flow.Flow

interface SuggestionRepository {

    suspend fun postBoard(
        imageUri: Uri?,
        title: String,
        content: String
    ) : Flow<ApiResult<BaseResponse<PostBoardResponse>>>

    suspend fun getBoardDetail(
        id: Int
    ) : Flow<ApiResult<BaseResponse<BoardDetailResponse>>>

    suspend fun postLikeBoard(
        id: Int
    ) : Flow<ApiResult<BaseResponse<Unit>>>

    suspend fun postComment(
        id: Int,
        content: String
    ) : Flow<ApiResult<BaseResponse<Unit>>>

    suspend fun getCommentList(
        id: Int,
        page: Int,
        size: Int
    ) : Flow<ApiResult<BaseResponse<CommentListResponse>>>
}