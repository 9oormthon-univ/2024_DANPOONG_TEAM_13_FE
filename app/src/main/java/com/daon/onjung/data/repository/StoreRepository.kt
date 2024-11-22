package com.daon.onjung.data.repository

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.SharedRestaurantListResponse
import com.daon.onjung.network.model.response.StoreDetailResponse
import com.daon.onjung.network.model.response.StoreOverviewResponse
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun getStoreList(page: Int, size: Int): Flow<ApiResult<BaseResponse<StoreOverviewResponse>>>

    suspend fun getStoreDetail(id: Int): Flow<ApiResult<BaseResponse<StoreDetailResponse>>>

    suspend fun putStoreOnjungShare(id: Int): Flow<ApiResult<BaseResponse<Unit>>>

    suspend fun getSharedRestaurantList(): Flow<ApiResult<BaseResponse<SharedRestaurantListResponse>>>
}