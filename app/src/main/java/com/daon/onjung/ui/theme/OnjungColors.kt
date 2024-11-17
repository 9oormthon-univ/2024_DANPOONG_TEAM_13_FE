package com.daon.onjung.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class OnjungColors(
    main_coral: Color = Color(0xFFFF7B69),
    sub_orange: Color = Color(0xFFFFB55F),
    sub_yellow: Color = Color(0xFFFFEFCF),
    sub_ivory: Color = Color(0xFFF8F9F4),
    white: Color = Color(0xFFFFFFFF),
    gray_1: Color = Color(0xFFF8F8F8),
    gray_2: Color = Color(0xFFEEEEEE),
    gray_3: Color = Color(0xFFD0D0D0),
    text_1: Color = Color(0xFF222222),
    text_2: Color = Color(0xFF616161),
    text_3: Color = Color(0xFF949494),
) {
    var main_coral by mutableStateOf(main_coral)
        private set
    var sub_orange by mutableStateOf(sub_orange)
        private set
    var sub_yellow by mutableStateOf(sub_yellow)
        private set
    var sub_ivory by mutableStateOf(sub_ivory)
        private set

    var white by mutableStateOf(white)
        private set

    var gray_1 by mutableStateOf(gray_1)
        private set
    var gray_2 by mutableStateOf(gray_2)
        private set
    var gray_3 by mutableStateOf(gray_3)
        private set

    var text_1 by mutableStateOf(text_1)
        private set
    var text_2 by mutableStateOf(text_2)
        private set
    var text_3 by mutableStateOf(text_3)
        private set

    fun copy(
        main_coral: Color = this.main_coral,
        sub_orange: Color = this.sub_orange,
        sub_yellow: Color = this.sub_yellow,
        sub_ivory: Color = this.sub_ivory,
        white: Color = this.white,
        gray_1: Color = this.gray_1,
        gray_2: Color = this.gray_2,
        gray_3: Color = this.gray_3,
        text_1: Color = this.text_1,
        text_2: Color = this.text_2,
        text_3: Color = this.text_3,
    ): OnjungColors {
        return OnjungColors(
            main_coral = main_coral,
            sub_orange = sub_orange,
            sub_yellow = sub_yellow,
            sub_ivory = sub_ivory,
            white = white,
            gray_1 = gray_1,
            gray_2 = gray_2,
            gray_3 = gray_3,
            text_1 = text_1,
            text_2 = text_2,
            text_3 = text_3,
        )
    }

    fun updateColorFrom(other: OnjungColors) {
        main_coral = other.main_coral
        sub_orange = other.sub_orange
        sub_yellow = other.sub_yellow
        sub_ivory = other.sub_ivory
        white = other.white
        gray_1 = other.gray_1
        gray_2 = other.gray_2
        gray_3 = other.gray_3
        text_1 = other.text_1
        text_2 = other.text_2
        text_3 = other.text_3
    }
}

val LocalColors = staticCompositionLocalOf { OnjungColors() }