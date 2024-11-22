package com.daon.onjung.network.service

import com.daon.onjung.network.adapter.ApiResult
import com.daon.onjung.network.model.BaseResponse
import com.daon.onjung.network.model.response.MealTicketListResponse
import com.daon.onjung.network.model.response.QRCodeResponse
import com.daon.onjung.network.model.response.TicketCountResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventService {

    @GET("/api/v1/tickets")
    suspend fun getTicketList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ApiResult<BaseResponse<MealTicketListResponse>>

    @GET("/api/v1/tickets/{id}/briefs")
    suspend fun getTicketBrief(
        @Query("id") id: Int
    ): ApiResult<BaseResponse<QRCodeResponse>>

    @GET("/api/v1/users/tickets/count")
    suspend fun getTicketCount(): ApiResult<BaseResponse<TicketCountResponse>>

}