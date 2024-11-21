package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class CompanyBriefResponse(
    @SerializedName("company_images")
    val companyImages: List<String>
)
