package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.StoreDetailResponse
import com.daon.onjung.network.model.response.StoreOverviewResponse
import kotlinx.coroutines.flow.Flow

interface StoreDataSource {

    suspend fun getStoreList(page: Int, size: Int): Flow<ApiResult<BaseResponse<StoreOverviewResponse>>>

    suspend fun getStoreDetail(id: Int): Flow<ApiResult<BaseResponse<StoreDetailResponse>>>

    suspend fun putStoreOnjungShare(id: Int): Flow<ApiResult<BaseResponse<Unit>>>

}