package com.daon.onjung.ui.setting

import com.daon.onjung.R

data class SettingButtonData(
    val text: String,
    val icon: Int = R.drawable.ic_arrow_right,
    val isToggle: Boolean = false,
    val onClick: () -> Unit
)