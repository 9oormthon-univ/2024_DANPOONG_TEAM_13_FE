package com.daon.onjung.di

import com.daon.onjung.data.datasource.AuthDataSource
import com.daon.onjung.data.datasource.AuthDataSourceImpl
import com.daon.onjung.data.datasource.DataStoreDataSource
import com.daon.onjung.data.datasource.DataStoreDataSourceImpl
import com.daon.onjung.data.datasource.OnjungDataSource
import com.daon.onjung.data.datasource.OnjungDataSourceImpl
import com.daon.onjung.data.repository.AuthRepository
import com.daon.onjung.data.repository.AuthRepositoryImpl
import com.daon.onjung.data.repository.DataStoreRepository
import com.daon.onjung.data.repository.DataStoreRepositoryImpl
import com.daon.onjung.data.repository.OnjungRepository
import com.daon.onjung.data.repository.OnjungRepositoryImpl
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

    @Binds
    abstract fun bindsAuthDataSource(
        authDataSource: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun bindsAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindsOnjungDataSource(
        onjungDataSource: OnjungDataSourceImpl
    ): OnjungDataSource

    @Binds
    abstract fun bindsOnjungRepository(
        onjungRepository: OnjungRepositoryImpl
    ): OnjungRepository

}