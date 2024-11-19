package com.daon.onjung.data.repository

import com.daon.onjung.Constants
import com.daon.onjung.data.datasource.DataStoreDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override fun getIsFirstLaunch(): Boolean = runBlocking {
        dataStoreDataSource.getBooleanValue(Constants.IS_FIRST_LAUNCH).first()
    }

    override fun setIsFirstLaunch(isFirstLaunch: Boolean) = runBlocking {
        dataStoreDataSource.setBooleanValue(Constants.IS_FIRST_LAUNCH, isFirstLaunch)
    }

    override fun getAccessToken(): String = runBlocking{
        dataStoreDataSource.getStringValue(Constants.ACCESS_TOKEN).first()
    }

    override fun setAccessToken(accessToken: String) = runBlocking {
        dataStoreDataSource.setStringValue(Constants.ACCESS_TOKEN, accessToken)
    }

    override fun getRefreshToken(): String = runBlocking {
        dataStoreDataSource.getStringValue(Constants.REFRESH_TOKEN).first()
    }

    override fun setRefreshToken(refreshToken: String) = runBlocking {
        dataStoreDataSource.setStringValue(Constants.REFRESH_TOKEN, refreshToken)
    }

    override fun deleteTokens() = runBlocking {
        dataStoreDataSource.deleteStringValue(Constants.ACCESS_TOKEN)
        dataStoreDataSource.deleteStringValue(Constants.REFRESH_TOKEN)
    }
}