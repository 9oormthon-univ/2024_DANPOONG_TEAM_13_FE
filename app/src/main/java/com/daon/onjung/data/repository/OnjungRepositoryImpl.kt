package com.daon.onjung.data.repository

import android.content.Context
import android.net.Uri
import com.daon.onjung.data.datasource.OnjungDataSource
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.PostReceiptRequest
import com.daon.onjung.network.model.response.OcrResponse
import com.daon.onjung.util.fileFromContentUri
import com.daon.onjung.util.resizeAndSaveImage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

class OnjungRepositoryImpl @Inject constructor(
    private val onjungDataSource: OnjungDataSource,
    @ApplicationContext private val context: Context
) : OnjungRepository {

    override fun getOnjungSummary() = onjungDataSource.getOnjungSummary()

    override fun postReceiptOcr(receiptImageUri: Uri): Flow<ApiResult<BaseResponse<OcrResponse>>> {
        val originalFile = fileFromContentUri(context, receiptImageUri)
        val compressedFile = resizeAndSaveImage(context, originalFile)

        val requestBody = compressedFile.asRequestBody("image/*".toMediaType())
        val receiptFile = MultipartBody.Part.createFormData("file", compressedFile.name, requestBody)

        return onjungDataSource.postReceiptOcr(receiptFile)
    }

    override fun postReceipt(
        storeName: String,
        storeAddress: String,
        paymentDate: String,
        paymentAmount: String
    ): Flow<ApiResult<BaseResponse<Any>>> {
        return onjungDataSource.postReceipt(
            PostReceiptRequest(storeName, storeAddress, paymentDate, paymentAmount)
        )
    }

    override fun getOnjungCount() = onjungDataSource.getOnjungCount()

    override fun getOnjungBrief() = onjungDataSource.getOnjungBrief()

    override fun postDonation(id: Int) = onjungDataSource.postPostDonation(id)
}