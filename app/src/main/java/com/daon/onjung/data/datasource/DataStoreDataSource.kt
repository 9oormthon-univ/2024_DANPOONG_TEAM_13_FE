package com.daon.onjung.data.datasource

import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {

    fun getIntValue(type: String): Flow<Int>
    suspend fun setIntValue(type: String, value: Int)

    fun getStringValue(type: String): Flow<String>
    suspend fun setStringValue(type: String, value: String)

    fun getBooleanValue(type: String): Flow<Boolean>
    suspend fun setBooleanValue(type: String, value: Boolean)

    suspend fun deleteStringValue(type: String)
    suspend fun deleteBooleanValue(type: String)
}