package com.daon.onjung.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun SearchButton(
    text: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = CircleShape,
        color = if(isSelected) Color(0xFF333D4B) else Color(0xFFFFFFFF),
        border = BorderStroke(
            width = 1.dp,
            color = if(isSelected) Color(0xFF333D4B) else Color(0xFFE5E8EB),
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).height(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = OnjungTheme.typography.body1.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                color = if(isSelected) Color(0xFFFFFFFF) else Color(0xFF6B7684)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchButton1Preview() {
    OnjungTheme{
        SearchButton(
            "전체",
            isSelected = true
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchButton2Preview() {
    OnjungTheme{
        SearchButton(
            "착한가격"
        )
    }
}

