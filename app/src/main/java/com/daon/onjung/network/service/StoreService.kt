package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.StoreDetailResponse
import com.daon.onjung.network.model.response.StoreOverviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreService {

    @GET("/api/v1/stores/overviews")
    suspend fun getStoreList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ApiResult<BaseResponse<StoreOverviewResponse>>

    @GET("/api/v1/stores/{id}/details")
    suspend fun getStoreDetail(
        @Path("id") id: Int
    ): ApiResult<BaseResponse<StoreDetailResponse>>

}