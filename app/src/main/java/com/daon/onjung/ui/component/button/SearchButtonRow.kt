package com.daon.onjung.ui.component.button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun SearchButtonRow(
    buttons: List<String>,
    selectedButton: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        buttons.forEachIndexed { index, button ->
            SearchButton(
                text = button,
                isSelected = selectedButton == button,
                onClick = {
                    onClick(button)
                },
            )
            if (index < buttons.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun SearchButtonRowPreview() {
    var selectedButton by remember { mutableStateOf("") }
    val buttons = listOf("전체", "최신", "인기", "오래된", "특별")
    OnjungTheme {
        SearchButtonRow(
            buttons = buttons,
            selectedButton = selectedButton,
            onClick = { button ->
                selectedButton = button
            }
        )
    }
}

