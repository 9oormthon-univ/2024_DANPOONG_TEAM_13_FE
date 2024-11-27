package com.daon.onjung.data.repository

import android.net.Uri
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.PostBoardResponse
import kotlinx.coroutines.flow.Flow

interface SuggestionRepository {

    suspend fun postBoard(
        imageUri: Uri?,
        title: String,
        content: String
    ) : Flow<ApiResult<BaseResponse<PostBoardResponse>>>
}