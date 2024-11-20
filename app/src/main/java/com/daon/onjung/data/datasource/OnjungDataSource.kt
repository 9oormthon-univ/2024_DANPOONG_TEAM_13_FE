package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import kotlinx.coroutines.flow.Flow

interface OnjungDataSource {

    fun getOnjungSummary(): Flow<ApiResult<BaseResponse<OnjungSummaryResponse>>>

}