package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun SettingDialog (
    text: String = "로그아웃 하시겠어요?",
    onConfirm: () -> Unit,
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
                Text(
                    text,
                    style = OnjungTheme.typography.h2.copy(
                        fontWeight = FontWeight.Bold,
                        color = OnjungTheme.colors.text_1
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    FilledWidthButton(
                        "취소",
                        modifier = Modifier
                            .weight(1f),
                        isEnabled = false,
                        fontSize = 16,
                        onClick = onDismissRequest
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    FilledWidthButton(
                        "확인",
                        modifier = Modifier
                            .weight(1f),
                        fontSize = 16,
                        onClick = onConfirm
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingDialogPreview() {
    OnjungTheme {
        SettingDialog (
            "정말로 탈퇴하시겠어요?",
            onConfirm = {}
        ){
            // modal 닫기 처리
        }
    }
}