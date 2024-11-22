package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.SharedRestaurantListResponse
import com.daon.onjung.network.model.response.StoreDetailResponse
import com.daon.onjung.network.model.response.StoreOverviewResponse
import com.daon.onjung.network.service.StoreService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    private val storeService: StoreService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : StoreDataSource {

    override suspend fun getStoreList(
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseResponse<StoreOverviewResponse>>> = flow {
        emit(storeService.getStoreList(page, size))
    }.flowOn(ioDispatcher)

    override suspend fun getStoreDetail(
        id: Int
    ): Flow<ApiResult<BaseResponse<StoreDetailResponse>>> = flow {
        emit(storeService.getStoreDetail(id))
    }.flowOn(ioDispatcher)

    override suspend fun putStoreOnjungShare(
        id: Int
    ): Flow<ApiResult<BaseResponse<Unit>>> = flow {
        emit(storeService.putStoreOnjungShare(id))
    }.flowOn(ioDispatcher)

    override suspend fun getSharedRestaurantList(): Flow<ApiResult<BaseResponse<SharedRestaurantListResponse>>> = flow {
        emit(storeService.getSharedRestaurantList())
    }.flowOn(ioDispatcher)

}