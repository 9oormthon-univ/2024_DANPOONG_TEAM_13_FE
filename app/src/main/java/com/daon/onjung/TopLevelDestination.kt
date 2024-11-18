package com.daon.onjung

import androidx.annotation.DrawableRes

enum class TopLevelDestination(
    val label: String,
    @DrawableRes val icon: Int,
    val route: String
) {
    Home(
        label = "홈",
        icon = R.drawable.ic_home,
        route = Routes.Home.ROUTE
    ),
    MailBox(
        label = "온기 우편함",
        icon = R.drawable.ic_mail,
        route = Routes.Mail.ROUTE
    ),
    Profile(
        label = "나의 온기",
        icon = R.drawable.ic_heart,
        route = Routes.Profile.ROUTE
    )
}