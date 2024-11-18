package com.daon.onjung.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme


@Composable
fun TopBar(
    content: String = "",
    @DrawableRes leftIcon: Int? = R.drawable.ic_back,
    @DrawableRes rightIcon: Int? = R.drawable.ic_close,
    trailingIcon: @Composable (() -> Unit) = {},
    leftIconOnClick: () -> Unit = {},
    rightIconOnClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leftIcon != null) {
                Icon(
                    painter = painterResource(id = leftIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { leftIconOnClick() }
                )
            }

            trailingIcon()
        }

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
            if (rightIcon != null) {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { rightIconOnClick() }
                )
            }

            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar1Preview() {
    OnjungTheme{
        TopBar(
            "식당 정보",
            rightIcon = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar2Preview() {
    OnjungTheme{
        TopBar(
            "나의 식권",
            rightIcon = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar3Preview() {
    OnjungTheme{
        TopBar(
            "온기 우편함",
            rightIcon = null,
            leftIcon = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar4Preview() {
    OnjungTheme{
        TopBar(
            rightIcon = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar5Preview() {
    OnjungTheme{
        TopBar(
            leftIcon = null,
        )
    }
}