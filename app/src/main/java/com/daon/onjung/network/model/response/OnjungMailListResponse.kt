package com.daon.onjung.network.model.response

import com.daon.onjung.network.model.OnjungMailStatus
import com.daon.onjung.network.model.OnjungType
import com.google.gson.annotations.SerializedName

data class OnjungMailListResponse(
    @SerializedName("event_list")
    val eventList: List<OnjungMailResponse>,
    @SerializedName("has_next")
    val hasNext: Boolean
)

data class OnjungMailResponse(
    @SerializedName("store_info")
    val storeInfo: OnjungMailStoreInfo,
    @SerializedName("onjung_type")
    val onjungType: OnjungType,
    @SerializedName("status")
    val status: OnjungMailStatus,
    @SerializedName("event_period")
    val eventPeriod: String,
    @SerializedName("store_delivery_date")
    val storeDeliveryDate: String?,
    @SerializedName("ticket_issue_date")
    val ticketIssueDate: String?,
    @SerializedName("report_date")
    val reportDate: String?,
)

data class OnjungMailStoreInfo(
    @SerializedName("logo_img_url")
    val logoImgUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("name")
    val name: String
)