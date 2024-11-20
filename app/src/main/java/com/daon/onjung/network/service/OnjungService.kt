package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostReceiptRequest
import com.daon.onjung.network.model.response.OcrResponse
import com.daon.onjung.network.model.response.OnjungBriefResponse
import com.daon.onjung.network.model.response.OnjungCountResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OnjungService {

    @GET("/api/v1/onjungs/summaries")
    suspend fun getOnjungSummary() : ApiResult<BaseResponse<OnjungSummaryResponse>>

    @Multipart
    @POST("/api/v1/receipts/ocr")
    suspend fun postReceiptOcr(
        @Part receiptFile: MultipartBody.Part
    ): ApiResult<BaseResponse<OcrResponse>>

    @POST("/api/v1/receipts")
    suspend fun postReceipt(
        @Body postReceiptRequest: PostReceiptRequest
    ): ApiResult<BaseResponse<Any>>

    @GET("/api/v1/onjungs/count")
    suspend fun getOnjungCount() : ApiResult<BaseResponse<OnjungCountResponse>>

    @GET("/api/v1/onjungs/briefs")
    suspend fun getOnjungBrief() : ApiResult<BaseResponse<OnjungBriefResponse>>
}