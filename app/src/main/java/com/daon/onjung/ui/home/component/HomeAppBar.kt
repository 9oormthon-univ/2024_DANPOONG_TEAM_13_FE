package com.daon.onjung.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.component.NoRippleInteractionSource
import com.daon.onjung.ui.theme.OnjungTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    onSettingClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = OnjungTheme.colors.white)
            .padding(
                start = 30.dp,
                end = 20.dp,
                top = 14.dp,
                bottom = 14.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home_logo),
            contentDescription = "IC_HOME_LOGO",
            tint = Color.Unspecified
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                Surface(
                    onClick = onSettingClick,
                    interactionSource = NoRippleInteractionSource()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "IC_SETTING",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeAppBarPreview() {
    OnjungTheme {
        HomeAppBar(
            onSettingClick = { }
        )
    }
}