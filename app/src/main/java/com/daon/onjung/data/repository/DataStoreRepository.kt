package com.daon.onjung.data.repository

interface DataStoreRepository {

    fun getIsNotFirstLaunch(): Boolean
    fun setIsNotFirstLaunch(isFirstLaunch: Boolean)

    fun getAccessToken(): String
    fun setAccessToken(accessToken: String)

    fun getRefreshToken(): String
    fun setRefreshToken(refreshToken: String)

    fun deleteTokens()
}