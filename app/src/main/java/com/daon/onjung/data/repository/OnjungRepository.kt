package com.daon.onjung.data.repository

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import kotlinx.coroutines.flow.Flow

interface OnjungRepository {

    fun getOnjungSummary() : Flow<ApiResult<BaseResponse<OnjungSummaryResponse>>>

}