package com.daon.onjung.data.repository

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.LoginProvider
import com.daon.onjung.network.model.response.UserTokenResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun kakaoLogin(
        accessToken: String,
        provider: LoginProvider
    ) : Flow<ApiResult<BaseResponse<UserTokenResponse>>>

    suspend fun patchDeviceToken(
        deviceToken: String
    ) : Flow<ApiResult<BaseResponse<Any>>>

    suspend fun logout() : Flow<ApiResult<BaseResponse<Any>>>

    suspend fun deleteAccount() : Flow<ApiResult<BaseResponse<Any>>>
}