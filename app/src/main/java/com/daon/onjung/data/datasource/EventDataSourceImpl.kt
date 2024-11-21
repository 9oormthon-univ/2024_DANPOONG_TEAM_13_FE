package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.MealTicketListResponse
import com.daon.onjung.network.model.response.QRCodeResponse
import com.daon.onjung.network.service.EventService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EventDataSourceImpl @Inject constructor(
    private val eventService: EventService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : EventDataSource {

    override suspend fun getTicketList(
        page: Int,
        size: Int
    ): Flow<ApiResult<BaseResponse<MealTicketListResponse>>> = flow {
        emit(eventService.getTicketList(page, size))
    }.flowOn(ioDispatcher)

    override suspend fun getTicketBrief(id: Int): Flow<ApiResult<BaseResponse<QRCodeResponse>>> = flow {
        emit(eventService.getTicketBrief(id))
    }.flowOn(ioDispatcher)

}