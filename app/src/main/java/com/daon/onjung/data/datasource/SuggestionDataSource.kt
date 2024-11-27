package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostCommentRequest
import com.daon.onjung.network.model.response.BoardDetailResponse
import com.daon.onjung.network.model.response.BoardListResponse
import com.daon.onjung.network.model.response.CommentDetail
import com.daon.onjung.network.model.response.CommentListResponse
import com.daon.onjung.network.model.response.LikeStatusResponse
import com.daon.onjung.network.model.response.PostBoardResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SuggestionDataSource {

    suspend fun postBoard(
        body: RequestBody,
        file: MultipartBody.Part?
    ) : Flow<ApiResult<BaseResponse<PostBoardResponse>>>

    suspend fun getBoardDetail(
        id: Int
    ) : Flow<ApiResult<BaseResponse<BoardDetailResponse>>>

    suspend fun putLikeBoard(
        id: Int
    ) : Flow<ApiResult<BaseResponse<LikeStatusResponse>>>

    suspend fun postComment(
        id: Int,
        postCommentRequest: PostCommentRequest
    ) : Flow<ApiResult<BaseResponse<CommentDetail>>>

    suspend fun getCommentList(
        id: Int,
        page: Int,
        size: Int
    ) : Flow<ApiResult<BaseResponse<CommentListResponse>>>

    suspend fun getBoards(
        page: Int,
        size: Int
    ) : Flow<ApiResult<BaseResponse<BoardListResponse>>>
}