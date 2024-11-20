package com.daon.onjung.data.datasource

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostReceiptRequest
import com.daon.onjung.network.model.response.OcrResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface OnjungDataSource {

    fun getOnjungSummary(): Flow<ApiResult<BaseResponse<OnjungSummaryResponse>>>

    fun postReceiptOcr(
        receiptFile: MultipartBody.Part
    ): Flow<ApiResult<BaseResponse<OcrResponse>>>

    fun postReceipt(
        postReceiptRequest: PostReceiptRequest
    ): Flow<ApiResult<BaseResponse<Any>>>

}