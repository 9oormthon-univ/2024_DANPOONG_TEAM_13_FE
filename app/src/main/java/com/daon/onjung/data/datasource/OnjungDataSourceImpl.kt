package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.service.OnjungService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OnjungDataSourceImpl @Inject constructor(
    private val onjungService: OnjungService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : OnjungDataSource {

    override fun getOnjungSummary() = flow {
        emit(onjungService.getOnjungSummary())
    }.flowOn(ioDispatcher)
}