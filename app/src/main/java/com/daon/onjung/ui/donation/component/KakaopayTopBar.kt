package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun KakaopayTopBar (
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_kakaopay),
            contentDescription = "IC_HOME_LOGO",
            tint = Color.Unspecified
        )
        Box(
            modifier = Modifier
                .clickable(onClick = onCloseClick)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakaopay_close),
                contentDescription = "IC_CLOSE",
                tint = Color(0xFF5E5E5E),
            )
        }
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
//                Surface(
//                    onClick = onCloseClick,
////                    interactionSource = NoRippleInteractionSource()
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_close),
//                        contentDescription = "IC_CLOSE",
//                    )
//                }
//            }
//        }
    }
}