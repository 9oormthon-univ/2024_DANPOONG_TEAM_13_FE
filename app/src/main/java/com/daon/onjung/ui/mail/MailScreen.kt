package com.daon.onjung.ui.mail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.component.ShopMailContainer
import com.daon.onjung.ui.component.TopBar
import com.daon.onjung.ui.theme.OnjungTheme

@Preview
@Composable
internal fun MailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        TopBar(
            "온기 우편함",
            rightIcon = null,
            leftIcon = null
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                MailBanner()
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Text(
                    "3개의 식당과 온기를 나누었어요.",
                    style = OnjungTheme.typography.body2.copy(
                        color = OnjungTheme.colors.text_3,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(listOf(true, false, true)) { isExpanded ->
                ShopMailContainer(
                    title = "헌신에 보답하는 감사의 식탁",
                    name = "한걸음 닭꼬치",
                    tag = "동참",
                    isExpanded = isExpanded,
                    onExpandChange = { }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
internal fun MailBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.main_coral,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp, end = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "온기를 나눈 식당의\n소식을 전해드려요.",
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.white
            )

            Icon(
                modifier = Modifier.offset(y = 3.dp),
                painter = painterResource(id = R.drawable.ic_mail_banner),
                contentDescription = "Mail Banner",
                tint = Color.Unspecified
            )
        }
    }
}
