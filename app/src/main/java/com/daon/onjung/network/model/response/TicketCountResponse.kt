package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class TicketCountResponse(
    @SerializedName("ticket_count")
    val ticketCount: Int
)
