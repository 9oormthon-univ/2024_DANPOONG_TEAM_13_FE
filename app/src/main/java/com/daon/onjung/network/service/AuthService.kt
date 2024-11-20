package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.DeviceTokenRequest
import com.daon.onjung.network.model.request.LoginRequest
import com.daon.onjung.network.model.response.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/oauth/login")
    suspend fun kakaoLogin(
        @Body loginRequest: LoginRequest
    ): ApiResult<BaseResponse<UserTokenResponse>>

    @POST("/api/v1/auth/reissue/token")
    suspend fun reissueToken(): Response<BaseResponse<UserTokenResponse>>

    @PATCH("/api/v1/auth/device-token")
    suspend fun patchDeviceToken(
        @Body deviceTokenRequest: DeviceTokenRequest
    ): ApiResult<BaseResponse<Any>>
}