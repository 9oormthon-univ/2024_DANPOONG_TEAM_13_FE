package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun Profile(
    name: String = "온정이",
    email: String = "example@example.com",
    imageUrl: String? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        ProfileImage(
            imageUrl = imageUrl
        )
        Spacer(modifier = Modifier.width(18.dp))
        ProfileInfo(
            name = name,
            email = email
        )
    }
}

@Composable
private fun ProfileInfo(
    name: String,
    email: String
) {
   Column {
        Text(
            name,
            style = OnjungTheme.typography.caption.copy(
                color = OnjungTheme.colors.text_2,
                fontWeight = FontWeight.Bold
            )
        )
       Text(
           email,
           style = OnjungTheme.typography.body1.copy(
               color = OnjungTheme.colors.text_3,
               fontWeight = FontWeight.Normal
           )
       )
   }
}