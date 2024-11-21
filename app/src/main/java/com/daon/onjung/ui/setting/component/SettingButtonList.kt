package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.setting.SettingButtonData
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun SettingButtonList (
    buttonList: List<SettingButtonData>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)) // 모서리를 둥글게 처리
            .background(OnjungTheme.colors.gray_3) // 배경색 설정
            .padding(start = 20.dp, end = 18.dp, top = 12.dp, bottom = 12.dp) // 내부 패딩 설정
    ) {
        buttonList.forEach { buttonData ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
                    .clickable { buttonData.onClick() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    buttonData.text,
                    style = OnjungTheme.typography.body1.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = OnjungTheme.colors.text_2
                    )
                )
                if(buttonData.isToggle) {
                    AnimatedToggle(
                        modifier = Modifier.shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(15.dp),
                            clip = false
                        )
                    )
                } else {
                    Icon(
                        painter = painterResource(id = buttonData.icon),
                        contentDescription = null,
                        tint = OnjungTheme.colors.text_1
                    )
                }
            }
        }
    }
}

val ButtonList1 = listOf(
    SettingButtonData(
        text = "PUSH 알림",
        isToggle = true,
        onClick = {}
    ),
)

val ButtonList2 = listOf(
    SettingButtonData(
        text = "PUSH 알림",
        icon = R.drawable.ic_arrow_right,
        onClick = {}
    ),
    SettingButtonData(
        text = "탈퇴하기",
        icon = R.drawable.ic_arrow_right,
        onClick = {}
    ),
)

@Preview(showBackground = true)
@Composable
fun SettingButtonListPreviewOne() {
    OnjungTheme {
        SettingButtonList(
            buttonList = ButtonList1
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SettingButtonListPreviewTwo() {
    OnjungTheme {
        SettingButtonList(
            buttonList = ButtonList2
        )
    }
}


