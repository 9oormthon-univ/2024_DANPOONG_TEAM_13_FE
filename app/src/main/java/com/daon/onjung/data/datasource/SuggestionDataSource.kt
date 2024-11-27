package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.BoardListResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SuggestionDataSource {

    suspend fun postBoard(
        body: RequestBody,
        file: MultipartBody.Part?
    ) : Flow<ApiResult<BaseResponse<Unit>>>

    suspend fun getBoard(
        page: Int,
        size: Int
    ) : Flow<ApiResult<BaseResponse<BoardListResponse>>>
}