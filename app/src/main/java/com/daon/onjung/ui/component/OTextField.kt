package com.daon.onjung.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun OTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp),
    maxLength: Int = 20,
    textStyle: TextStyle = OnjungTheme.typography.body1,
    textColor: Color = OnjungTheme.colors.text_1,
    hintColor: Color = Color(0xFFC4C4C4),
    placeholderText: String = ""
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        modifier = modifier,
        textStyle = textStyle.copy(
            color = textColor
        ),
        cursorBrush = SolidColor(OnjungTheme.colors.main_coral),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.padding(contentPaddingValues)) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholderText,
                        style = textStyle.copy(
                            color = hintColor
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun GTextFieldPreview() {
    OnjungTheme {
        var text by remember { mutableStateOf("") }

        OTextField(
            value = text ,
            onValueChange = { text = it },
            placeholderText = "제목을 입력하세요"
        )
    }
}