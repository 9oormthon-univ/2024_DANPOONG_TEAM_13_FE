package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.MealTicketListResponse
import com.daon.onjung.network.model.response.QRCodeResponse
import kotlinx.coroutines.flow.Flow

interface EventDataSource {

    suspend fun getTicketList(page: Int, size: Int): Flow<ApiResult<BaseResponse<MealTicketListResponse>>>

    suspend fun getTicketBrief(id: Int): Flow<ApiResult<BaseResponse<QRCodeResponse>>>
}