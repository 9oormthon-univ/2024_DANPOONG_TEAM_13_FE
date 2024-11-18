package com.daon.onjung.ui.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ShowMoreButton(
    text: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 2.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = OnjungTheme.typography.caption
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Arrow Right Icon",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowMoreButtonPreview(

) {
    OnjungTheme{
        ShowMoreButton(
            "전체보기"
        ) {

        }
    }
}
