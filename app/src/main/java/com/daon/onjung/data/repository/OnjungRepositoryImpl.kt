package com.daon.onjung.data.repository

import com.daon.onjung.data.datasource.OnjungDataSource
import javax.inject.Inject

class OnjungRepositoryImpl @Inject constructor(
    private val onjungDataSource: OnjungDataSource
) : OnjungRepository {

    override fun getOnjungSummary() = onjungDataSource.getOnjungSummary()

}