package com.daon.onjung.data.datasource

import com.daon.onjung.di.IoDispatcher
import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.CompanyBriefResponse
import com.daon.onjung.network.service.CompanyService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CompanyDataSourceImpl @Inject constructor(
    private val companyService: CompanyService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CompanyDataSource {

    override suspend fun getCompanyBrief(): Flow<ApiResult<BaseResponse<CompanyBriefResponse>>> = flow {
        emit(companyService.getCompanyBrief())
    }.flowOn(ioDispatcher)

}