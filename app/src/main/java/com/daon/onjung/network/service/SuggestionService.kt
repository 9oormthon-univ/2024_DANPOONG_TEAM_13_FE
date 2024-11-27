package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.BoardListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SuggestionService {

    @Multipart
    @POST("/api/v1/boards")
    suspend fun postBoard(
        @Part("body") request: RequestBody,
        @Part file: MultipartBody.Part?
    ) : ApiResult<BaseResponse<Unit>>

    @GET("/api/v1/boards/overviews")
    suspend fun getBoards(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : ApiResult<BaseResponse<BoardListResponse>>
}