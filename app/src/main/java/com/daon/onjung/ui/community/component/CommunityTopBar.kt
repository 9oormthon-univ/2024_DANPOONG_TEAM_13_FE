package com.daon.onjung.ui.community.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Preview
@Composable
fun CommunityTopBar(
    modifier : Modifier = Modifier,
    title: String = "가게 추천"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(OnjungTheme.colors.white)
            .padding(
                vertical = 16.dp,
                horizontal = 20.dp
            )
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = OnjungTheme.typography.h2.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
        )
    }
}