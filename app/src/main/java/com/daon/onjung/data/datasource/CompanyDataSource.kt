package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.CompanyBriefResponse
import kotlinx.coroutines.flow.Flow

interface CompanyDataSource {

    suspend fun getCompanyBrief(): Flow<ApiResult<BaseResponse<CompanyBriefResponse>>>

}