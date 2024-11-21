package com.daon.onjung.data.repository

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.CompanyBriefResponse
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {

    suspend fun getCompanyBrief(): Flow<ApiResult<BaseResponse<CompanyBriefResponse>>>

}