package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostCommentRequest
import com.daon.onjung.network.model.response.BoardDetailResponse
import com.daon.onjung.network.model.response.BoardListResponse
import com.daon.onjung.network.model.response.CommentDetail
import com.daon.onjung.network.model.response.CommentListResponse
import com.daon.onjung.network.model.response.LikeStatusResponse
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
    ): Flow<ApiResult<BaseResponse<BoardDetailResponse>>> = flow {
        emit(suggestionService.getBoardDetail(id))
    }.flowOn(ioDispatcher)

    override suspend fun putLikeBoard(
        id: Int
    ): Flow<ApiResult<BaseResponse<LikeStatusResponse>>> = flow {
        emit(suggestionService.putLikeBoard(id))
    }.flowOn(ioDispatcher)

    override suspend fun postComment(
        id: Int,
        postCommentRequest: PostCommentRequest
    ): Flow<ApiResult<BaseResponse<CommentDetail>>> = flow {
        emit(suggestionService.postComment(id, postCommentRequest))
    }.flowOn(ioDispatcher)

    override suspend fun getCommentList(
        id: Int,
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseResponse<CommentListResponse>>> = flow {
        emit(suggestionService.getCommentList(id, page, size))
    }.flowOn(ioDispatcher)

    override suspend fun getBoards(
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseResponse<BoardListResponse>>> = flow {
        emit(suggestionService.getBoards(page, size))
    }.flowOn(ioDispatcher)
}
