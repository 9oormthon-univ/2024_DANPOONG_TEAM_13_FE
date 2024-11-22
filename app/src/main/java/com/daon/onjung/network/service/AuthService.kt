package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.DeviceTokenRequest
import com.daon.onjung.network.model.request.LoginRequest
import com.daon.onjung.network.model.response.NotificationAllowedResponse
import com.daon.onjung.network.model.response.ProfileResponse
import com.daon.onjung.network.model.response.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
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

    @POST("/api/v1/auth/logout")
    suspend fun logout(): ApiResult<BaseResponse<Any>>

    @DELETE("/api/v1/auth")
    suspend fun deleteAccount(): ApiResult<BaseResponse<Any>>

    @GET("/api/v1/users/overviews")
    suspend fun getUserProfile(): ApiResult<BaseResponse<ProfileResponse>>

    @GET("/api/v1/users/notification-allowed")
    suspend fun patchNotificationAllowed(): ApiResult<BaseResponse<NotificationAllowedResponse>>
}