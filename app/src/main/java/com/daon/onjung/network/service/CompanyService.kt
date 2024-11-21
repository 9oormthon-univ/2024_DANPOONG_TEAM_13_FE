package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.CompanyBriefResponse
import retrofit2.http.GET

interface CompanyService {

    @GET("/api/v1/company/briefs")
    suspend fun getCompanyBrief() : ApiResult<BaseResponse<CompanyBriefResponse>>

}