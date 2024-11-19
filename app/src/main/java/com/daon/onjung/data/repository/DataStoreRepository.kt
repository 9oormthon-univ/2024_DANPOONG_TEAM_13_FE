package com.daon.onjung.data.repository

interface DataStoreRepository {

    fun getIsFirstLaunch(): Boolean
    fun setIsFirstLaunch(isFirstLaunch: Boolean)

    fun getAccessToken(): String
    fun setAccessToken(accessToken: String)

    fun getRefreshToken(): String
    fun setRefreshToken(refreshToken: String)

    fun deleteTokens()
}