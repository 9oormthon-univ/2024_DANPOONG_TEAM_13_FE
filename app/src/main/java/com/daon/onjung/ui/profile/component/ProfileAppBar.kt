package com.daon.onjung.ui.profile.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ProfileTopBar(
    content: String = "",
    count: Int = 0,
    trailingIcon: @Composable (() -> Unit) = {},
    rightIconOnClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = content,
            style = OnjungTheme.typography.h2.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = OnjungTheme.colors.gray_3,
                border = BorderStroke(
                    width = 1.dp,
                    color = OnjungTheme.colors.gray_1,
                ),
                onClick = rightIconOnClick
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_coupon),
                        contentDescription = "Switch Icon",
                        modifier = Modifier.padding(end = 6.dp),
                        tint = if (count > 0) OnjungTheme.colors.main_coral  else OnjungTheme.colors.gray_1 ,
                    )
                    Text(
                        text = "$count",
                        style = OnjungTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
            }
            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileTopBar1Preview() {
    OnjungTheme{
        ProfileTopBar(
            "나의 온기",
            count = 0,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileTopBar2Preview() {
    OnjungTheme{
        ProfileTopBar(
            "나의 온기",
            count = 13,
        )
    }
}