package com.daon.onjung.data.repository

import com.daon.onjung.data.datasource.CompanyDataSource
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyDataSource: CompanyDataSource
) : CompanyRepository {

    override suspend fun getCompanyBrief() = companyDataSource.getCompanyBrief()

}