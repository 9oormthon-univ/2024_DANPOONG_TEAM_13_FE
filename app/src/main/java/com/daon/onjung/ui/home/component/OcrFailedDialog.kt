package com.daon.onjung.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.daon.onjung.R
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun OcrFailedDialog(
    title: String,
    description: String,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest
    ) {
        Box(
            modifier = Modifier.background(
                color = OnjungTheme.colors.white,
                shape = RoundedCornerShape(14.dp)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp, bottom = 20.dp,
                        start = 20.dp, end = 20.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "IC_WARNING",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    title,
                    style = OnjungTheme.typography.h2.copy(
                        fontWeight = FontWeight.Bold,
                        color = OnjungTheme.colors.text_1
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    description,
                    style = OnjungTheme.typography.body2.copy(
                        color = OnjungTheme.colors.text_2
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilledWidthButton(
                    text = "확인"
                ) {
                    onDismissRequest()
                }
            }
        }
    }
}

@Preview
@Composable
fun OcrFailedDialogPreview() {
    OnjungTheme {
        OcrFailedDialog(
            title = "인식 실패",
            description = "영수증 전체를 다시 촬영해주세요",
            onDismissRequest = {})
    }
}