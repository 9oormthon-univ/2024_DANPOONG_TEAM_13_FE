package com.daon.onjung.network.model.response

import com.google.gson.annotations.SerializedName

data class QRCodeResponse(
    @SerializedName("qr_base64")
    val qrBase64: String
)
