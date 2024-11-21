package com.daon.onjung.data.repository

import android.net.Uri
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.DonationResponse
import com.daon.onjung.network.model.response.OcrResponse
import com.daon.onjung.network.model.response.OnjungBriefResponse
import com.daon.onjung.network.model.response.OnjungCountResponse
import com.daon.onjung.network.model.response.OnjungSummaryResponse
import kotlinx.coroutines.flow.Flow

interface OnjungRepository {

    fun getOnjungSummary() : Flow<ApiResult<BaseResponse<OnjungSummaryResponse>>>

    fun postReceiptOcr(receiptImageUri: Uri) : Flow<ApiResult<BaseResponse<OcrResponse>>>

    fun postReceipt(
        storeName: String,
        storeAddress: String,
        paymentDate: String,
        paymentAmount: String
    ) : Flow<ApiResult<BaseResponse<Any>>>

    fun getOnjungCount() : Flow<ApiResult<BaseResponse<OnjungCountResponse>>>

    fun getOnjungBrief() : Flow<ApiResult<BaseResponse<OnjungBriefResponse>>>

    fun postDonation(id: Int) : Flow<ApiResult<BaseResponse<DonationResponse>>>

}