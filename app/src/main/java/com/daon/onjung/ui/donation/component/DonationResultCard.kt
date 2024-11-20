package com.daon.onjung.ui.donation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun DonationResultCard (
    modifier: Modifier = Modifier,
    date: String = "2024. 10. 31"
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color(0xFFFFC7CE), shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xFFFFF0EF))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "온기 증서",
                style = OnjungTheme.typography.caption.copy(
                    color = OnjungTheme.colors.text_2
                )
            )
            Text(
                date,
                style = OnjungTheme.typography.caption.copy(
                    color = OnjungTheme.colors.text_2
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_donation_char),
            contentDescription = "App Logo",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(25.dp))
        HorizontalDashedLineCanvas()
        Spacer(modifier = Modifier.height(16.dp))
        DonationStoreCard(
            modifier = Modifier.padding(start = 18.dp)
        )
    }
}

@Composable
private fun HorizontalDashedLineCanvas(
    color: Color = Color(0xFFFFC7CE),
) {
    Canvas(modifier = Modifier.fillMaxWidth().height(1.dp)) {
        drawLine(
            color = color,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }
}

@Composable
private fun DonationStoreCard(
    tag: String = "국가유공자",
    name: String = "한걸음 닭꼬치",
    modifier: Modifier = Modifier,
    imageUrl: String = ""
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(55.dp)
                .background(
                    color = OnjungTheme.colors.main_coral,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            model = ImageRequest.Builder(context).data(imageUrl).build(),
            contentDescription = "IMG_SHOP",
            placeholder = painterResource(id = R.drawable.img_dummy)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = tag,
                style = OnjungTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = OnjungTheme.colors.white
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(0xFF81A5DA))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
            Text(
                text = name,
                style = OnjungTheme.typography.h2.copy(
                    color = OnjungTheme.colors.text_1,
                ),
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DonationResultCardPreview() {
    OnjungTheme {
        DonationResultCard()
    }
}
