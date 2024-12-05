package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.network.model.StoreCategory
import com.daon.onjung.network.model.StoreTag
import com.daon.onjung.network.model.response.StoreDetailInfo
import com.daon.onjung.ui.theme.OnjungTheme

@Preview(showBackground = true)
@Composable
fun DonationStore(
    shopInfo : StoreDetailInfo = StoreDetailInfo("", listOf(StoreTag.DISABLED_GROUP), "", "", "", "", StoreCategory.KOREAN, "", 0.0, 0.0, "")
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = OnjungTheme.colors.white)
            .padding(vertical = 28.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(75.dp)
                .background(
                    color = OnjungTheme.colors.main_coral,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(context).data(shopInfo.logoImgUrl).build(),
            contentDescription = "IMG_SHOP",
            placeholder = painterResource(id = R.drawable.img_dummy),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = shopInfo.title,
                style = OnjungTheme.typography.h2,
                color = OnjungTheme.colors.text_1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = shopInfo.name,
                style = OnjungTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OnjungTheme.colors.text_3
                ),
            )
        }
    }
}