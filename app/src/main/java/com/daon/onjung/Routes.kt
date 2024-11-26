package com.daon.onjung

object Routes {
    object Auth {
        const val ROUTE = "authRoute"

        const val SPLASH = "splash"
        const val LOGIN = "login"
    }

    object Home {
        const val ROUTE = "homeRoute"

        const val SHOP_DETAIL = "shopDetail"
        const val OCR_CAMERA = "ocrCamera"
    }

    object Mail {
        const val ROUTE = "mailBoxRoute"
    }

    object Community {
        const val ROUTE = "communityRoute"
    }

    object Profile {
        const val ROUTE = "profileRoute"

        const val RESTAURANT_LIST = "restaurantList"
        const val TICKET_LIST = "ticketList"
    }

    object Setting {
        const val ROUTE = "settingRoute"
    }

    object Donation {
        const val ROUTE = "donationRoute"

        const val KAKAOPAY = "kakaopay"
        const val KAKAOPAYPAYMENT = "kakaopayPayment"
        const val KAKAOPAYRESULT = "kakaopayResult"
        const val DONDATIONRESULT = "donationResult"
    }

    const val CELEBRATION = "celebration"
}