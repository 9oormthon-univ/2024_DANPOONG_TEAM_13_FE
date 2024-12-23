package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostCommentRequest
import com.daon.onjung.network.model.response.BoardDetailResponse
import com.daon.onjung.network.model.response.CommentDetail
import com.daon.onjung.network.model.response.CommentListResponse
import com.daon.onjung.network.model.response.LikeStatusResponse
import com.daon.onjung.network.model.response.PostBoardResponse
import com.daon.onjung.network.model.response.BoardListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SuggestionService {

    @Multipart
    @POST("/api/v1/boards")
    suspend fun postBoard(
        @Part("body") request: RequestBody,
        @Part file: MultipartBody.Part?
    ) : ApiResult<BaseResponse<PostBoardResponse>>

    @GET("/api/v1/boards/{id}/details")
    suspend fun getBoardDetail(
        @Path("id") id: Int
    ) : ApiResult<BaseResponse<BoardDetailResponse>>

    @PUT("/api/v1/boards/{id}/likes")
    suspend fun putLikeBoard(
        @Path("id") id: Int
    ) : ApiResult<BaseResponse<LikeStatusResponse>>

    @POST("/api/v1/boards/{id}/comments")
    suspend fun postComment(
        @Path("id") id: Int,
        @Body postCommentRequest: PostCommentRequest
    ) : ApiResult<BaseResponse<CommentDetail>>

    @GET("/api/v1/boards/{id}/comments/overviews")
    suspend fun getCommentList(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : ApiResult<BaseResponse<CommentListResponse>>

    @GET("/api/v1/boards/overviews")
    suspend fun getBoards(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : ApiResult<BaseResponse<BoardListResponse>>
}