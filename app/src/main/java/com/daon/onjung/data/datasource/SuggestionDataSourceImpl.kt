package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostCommentRequest
import com.daon.onjung.network.model.response.CommentListResponse
import com.daon.onjung.network.model.response.PostBoardResponse
import com.daon.onjung.network.service.SuggestionService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

data class SuggestionDataSourceImpl @Inject constructor(
    private val suggestionService: SuggestionService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SuggestionDataSource {

    override suspend fun postBoard(
        body: RequestBody,
        file: MultipartBody.Part?
    ): Flow<ApiResult<BaseResponse<PostBoardResponse>>> = flow {
        emit(suggestionService.postBoard(body, file))
    }.flowOn(ioDispatcher)

    override suspend fun getBoardDetail(
        id: Int
    ): Flow<ApiResult<BaseResponse<PostBoardResponse>>> = flow {
        emit(suggestionService.getBoardDetail(id))
    }.flowOn(ioDispatcher)

    override suspend fun postLikeBoard(
        id: Int
    ): Flow<ApiResult<BaseResponse<Unit>>> = flow {
        emit(suggestionService.postLikeBoard(id))
    }.flowOn(ioDispatcher)

    override suspend fun postComment(
        id: Int,
        postCommentRequest: PostCommentRequest
    ): Flow<ApiResult<BaseResponse<Unit>>> = flow {
        emit(suggestionService.postComment(id, postCommentRequest))
    }.flowOn(ioDispatcher)

    override suspend fun getCommentList(
        id: Int,
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseResponse<CommentListResponse>>> = flow {
        emit(suggestionService.getCommentList(id, page, size))
    }.flowOn(ioDispatcher)

}
