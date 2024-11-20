package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.request.LoginRequest
import com.daon.onjung.network.model.response.UserTokenResponse
import com.daon.onjung.network.service.AuthService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthDataSource {

    override suspend fun kakaoLogin(
        loginRequest: LoginRequest
    ): Flow<ApiResult<BaseResponse<UserTokenResponse>>> = flow {
        emit(authService.kakaoLogin(loginRequest))
    }.flowOn(ioDispatcher)
}