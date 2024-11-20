package com.daon.onjung.data.repository

import com.daon.onjung.data.datasource.AuthDataSource
import com.daon.onjung.network.model.LoginProvider
import com.daon.onjung.network.model.request.LoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun kakaoLogin(
        accessToken: String,
        provider: LoginProvider
    ) = authDataSource.kakaoLogin(LoginRequest(accessToken, provider))

}