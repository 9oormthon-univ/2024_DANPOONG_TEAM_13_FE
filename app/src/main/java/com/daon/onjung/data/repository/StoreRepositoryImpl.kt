package com.daon.onjung.data.repository

import com.daon.onjung.data.datasource.StoreDataSource
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val storeDataSource: StoreDataSource,
) : StoreRepository {

    override suspend fun getStoreList(
        page: Int,
        size: Int
    ) = storeDataSource.getStoreList(page, size)

    override suspend fun getStoreDetail(id: Int) = storeDataSource.getStoreDetail(id)

    override suspend fun putStoreOnjungShare(id: Int) = storeDataSource.putStoreOnjungShare(id)

}