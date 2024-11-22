package com.daon.onjung.data.repository

import com.daon.onjung.data.datasource.EventDataSource
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDataSource: EventDataSource
) : EventRepository {

    override suspend fun getTicketList(
        page: Int,
        size: Int
    ) = eventDataSource.getTicketList(page, size)

    override suspend fun getTicketBrief(id: Int) =
        eventDataSource.getTicketBrief(id)

    override suspend fun getTicketCount() =
        eventDataSource.getTicketCount()

    override suspend fun getOnjungMailList(page: Int, size: Int) =
        eventDataSource.getOnjungMailList(page, size)
}