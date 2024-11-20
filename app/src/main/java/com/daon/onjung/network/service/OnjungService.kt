package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import retrofit2.http.GET

interface OnjungService {

    @GET("/api/v1/onjungs/summaries")
    suspend fun getOnjungSummary() : ApiResult<BaseResponse<OnjungSummaryResponse>>

}