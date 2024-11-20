package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun KakaoPrivateCheck(
    modifier: Modifier = Modifier,
    idChecked: Boolean = false,
    onCheckBtnClick: () -> Unit = {},
    onPrivateClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = if (idChecked) painterResource(id = R.drawable.ic_checked) else painterResource(id = R.drawable.ic_unchecked),
                contentDescription = "checked Icon",
                tint = Color.Unspecified,
                modifier = Modifier.clickable(
                    indication = null, // 클릭 시 시각적 피드백을 제거
                    interactionSource = remember { MutableInteractionSource() } // 상호작용 상태를 기억합니다
                ) {
                    onCheckBtnClick()
                }
            )
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                "(필수) 개인(신용)정보 제공 동의",
                style = OnjungTheme.typography.body1.copy(
                    color = Color.Black
                )
            )
        }
        Text(
            "보기",
            style = OnjungTheme.typography.body1.copy(
                color = OnjungTheme.colors.text_2
            ),
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable{
                onPrivateClick()
            }

        )
    }

}