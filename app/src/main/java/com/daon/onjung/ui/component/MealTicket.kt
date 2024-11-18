package com.daon.onjung.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

enum class TicketState {
    AVAILABLE, USED, EXPIRED
}

@Composable
fun MealTicket(
    name: String,
    imageUrl: String,
    tag: String,
    address: String,
    state: TicketState,
    date: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.background(
            color = OnjungTheme.colors.white,
            shape = TicketShape(
                circleRadius = 15.dp,
                cornerRadius = 10.dp
            )
        ).graphicsLayer {
            if (state != TicketState.AVAILABLE) alpha = 0.6f
        }
    ) {
        MealTicketHeader(
            name = name,
            imageUrl = imageUrl,
            tag = tag,
            address = address
        )
        HorizontalDottedLine(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp))
        MealTicketFooter(
            state = state,
            date = date,
            onClick = onClick
        )
    }
}

@Composable
private fun MealTicketHeader(
    name: String,
    imageUrl: String,
    tag: String,
    address: String
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = OnjungTheme.colors.main_coral,
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                model = ImageRequest.Builder(context).data(imageUrl).build(),
                contentDescription = "IMG_SHOP",
                placeholder = painterResource(id = R.drawable.img_dummy)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = OnjungTheme.typography.h2,
                        color = OnjungTheme.colors.text_1,
                    )

                    Text(
                        text = tag,
                        style = OnjungTheme.typography.caption,
                        color = OnjungTheme.colors.text_3
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gps),
                        contentDescription = "IC_GPS",
                        tint = Color.Unspecified
                    )

                    Text(
                        text = address,
                        style = OnjungTheme.typography.body2,
                        color = OnjungTheme.colors.text_3,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun MealTicketFooter(
    state: TicketState,
    date: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (state == TicketState.AVAILABLE || state == TicketState.EXPIRED) "$date 까지" else " $date 사용",
            style = OnjungTheme.typography.body2,
            color = OnjungTheme.colors.text_3
        )

        Button(
            onClick = onClick,
            contentPadding = PaddingValues(
                horizontal = 16.dp, vertical = 6.dp
            ),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = OnjungTheme.colors.main_coral,
                contentColor = OnjungTheme.colors.white,
                disabledContainerColor = if (state == TicketState.USED) OnjungTheme.colors.main_coral else Color(0xFF8B8B8B),
            ),
            enabled = state == TicketState.AVAILABLE
        ) {
            Text(
                text = when (state) {
                    TicketState.AVAILABLE -> "사용하기"
                    TicketState.USED -> "사용 완료"
                    TicketState.EXPIRED -> "기간 만료"
                },
                style = OnjungTheme.typography.body2,
                color = OnjungTheme.colors.white
            )
        }
    }
}

@Preview
@Composable
fun MealTicketPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MealTicket(
            name = "한걸음 닭꼬치",
            imageUrl = "https://via.placeholder.com/150",
            tag = "일식",
            address = "송파구 오금로 533 1층 (거여동)",
            state = TicketState.AVAILABLE,
            date = "2024. 12. 31",
            onClick = { }
        )

        MealTicket(
            name = "한걸음 닭꼬치",
            imageUrl = "https://via.placeholder.com/150",
            tag = "일식",
            address = "송파구 오금로 533 1층 (거여동)",
            state = TicketState.USED,
            date = "2024. 12. 31",
            onClick = { }
        )

        MealTicket(
            name = "한걸음 닭꼬치",
            imageUrl = "https://via.placeholder.com/150",
            tag = "일식",
            address = "송파구 오금로 533 1층 (거여동)",
            state = TicketState.EXPIRED,
            date = "2024. 12. 31",
            onClick = { }
        )
    }
}

class TicketShape(
    private val circleRadius: Dp,
    private val cornerRadius: Dp
) : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Generic(path = getPath(size, density))
    }

    private fun getPath(size: Size, density: Density): Path {
        val cornerRadiusToPx = with(density) { cornerRadius.toPx() }
        val roundedRect = androidx.compose.ui.geometry.RoundRect(size.toRect(), CornerRadius(cornerRadiusToPx))
        val roundedRectPath = Path().apply { addRoundRect(roundedRect) }
        return Path.combine(operation = PathOperation.Intersect, path1 = roundedRectPath, path2 = getTicketPath(size, density))
    }

    private fun getTicketPath(size: Size, density: Density): Path {
        val middleY = size.height / 2
        val circleRadiusInPx = with(density) { circleRadius.toPx() }
        return Path().apply {
            reset()
            lineTo(x = 0F, y = 0F)
            lineTo(x = size.width, y = 0F)
            lineTo(x = size.width, y = middleY - circleRadiusInPx)
            arcTo(
                rect = Rect(
                    left = size.width - circleRadiusInPx,
                    top = middleY - circleRadiusInPx + 10f,
                    right = size.width + circleRadiusInPx,
                    bottom = middleY + circleRadiusInPx + 10f
                ),
                startAngleDegrees = 270F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )
            lineTo(x = size.width, y = size.height)
            lineTo(x = 0F, y = size.height)
            lineTo(x = 0F, y = middleY + circleRadiusInPx)
            arcTo(
                rect = Rect(
                    left = -circleRadiusInPx,
                    top = middleY - circleRadiusInPx + 10f,
                    right = circleRadiusInPx,
                    bottom = middleY + circleRadiusInPx + 10f
                ),
                startAngleDegrees = 90F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )
            // Draw line back to top left
            lineTo(x = 0F, y = 0F)
        }
    }
}

