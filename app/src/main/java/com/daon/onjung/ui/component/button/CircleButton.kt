package com.daon.onjung.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Surface
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
fun CircleButton(
    text: String = "",
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = R.drawable.ic_heart,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .size(80.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = OnjungTheme.colors.white,
        onClick = onClick
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                icon?.let {
                    Icon(

                        painter = painterResource(id = it),
                        contentDescription = "",
                        tint = OnjungTheme.colors.main_coral,
                        modifier = Modifier.offset(0.dp, (-3).dp)
                    )
                }
                Text(
                    modifier = Modifier.offset(0.dp, (-4).dp),
                    text = text,
                    style = OnjungTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        color = OnjungTheme.colors.main_coral
                    ),
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun FloatButton1Preview( ) {
    OnjungTheme {
        CircleButton(
            "영수증 인증",
            icon = R.drawable.ic_heart
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FloatButton2Preview( ) {
    OnjungTheme {
        CircleButton(
            "온기 공유",
            icon = R.drawable.ic_invoice
        )
    }
}