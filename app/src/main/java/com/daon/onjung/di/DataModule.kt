package com.daon.onjung.di

import com.daon.onjung.data.datasource.DataStoreDataSource
import com.daon.onjung.data.datasource.DataStoreDataSourceImpl
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.data.repository.DataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsDataStoreDataSource(
        dataStoreDataSource: DataStoreDataSourceImpl
    ): DataStoreDataSource

    @Binds
    abstract fun bindsDataStoreRepository(
        dataStoreRepository: DataStoreRepositoryImpl
    ): DataStoreRepository

}