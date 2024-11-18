package com.daon.onjung.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        shadowElevation = 10.dp,
        color = backgroundColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .selectableGroup()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                content()
            }
        }
    }
}

@Composable
fun BottomNavigationBarItem(
    modifier: Modifier = Modifier,
    label: String,
    selected: Boolean,
    @DrawableRes icon: Int,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    onClick: () -> Unit
) {
    val contentColor = if (selected) selectedContentColor else unselectedContentColor

    Surface(
        modifier = modifier,
        color = Color.Transparent,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = label,
                    tint = contentColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = label,
                    style = OnjungTheme.typography.caption.copy(
                        color = contentColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    showSystemUi = true
)
@Composable
fun BottomNavigationBarPreview() {
    OnjungTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(OnjungTheme.colors.white)
        ) {
            BottomNavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                backgroundColor = Color.White
            ) {
                BottomNavigationBarItem(
                    label = "홈",
                    selected = true,
                    icon = R.drawable.ic_home,
                    selectedContentColor = OnjungTheme.colors.main_coral,
                    unselectedContentColor = OnjungTheme.colors.gray_1,
                    onClick = {}
                )
                BottomNavigationBarItem(
                    label = "온기 우편함",
                    selected = false,
                    icon = R.drawable.ic_mail,
                    selectedContentColor = OnjungTheme.colors.main_coral,
                    unselectedContentColor = OnjungTheme.colors.gray_1,
                    onClick = {}
                )
                BottomNavigationBarItem(
                    label = "나의 온기",
                    selected = false,
                    icon = R.drawable.ic_heart,
                    selectedContentColor = OnjungTheme.colors.main_coral,
                    unselectedContentColor = OnjungTheme.colors.gray_1,
                    onClick = {}
                )
            }
        }
    }
}