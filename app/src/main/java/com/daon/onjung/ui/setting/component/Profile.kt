package com.daon.onjung.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    name: String = "온정이",
    imageUrl: String = "",
) {
    val context = LocalContext.current
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        AsyncImage(
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .error(R.drawable.ic_profile_icon)
                .fallback(R.drawable.ic_profile_icon)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Image",
            placeholder = painterResource(id = R.drawable.ic_profile_icon)
        )
        Spacer(modifier = Modifier.width(18.dp))
        ProfileInfo(
            name = name
        )
    }
}

@Composable
private fun ProfileInfo(
    name: String,
) {
   Column {
       Text(
           name,
           style = OnjungTheme.typography.title.copy(
               color = OnjungTheme.colors.text_2
           )
       )
   }
}