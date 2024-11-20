
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun ReceiptConfirmBottomSheet(
    name: String,
    address: String,
    visitDate: String,
    spentAmount: String,
    onConfirm: () -> Unit,
    hideBottomSheet: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = OnjungTheme.colors.white,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_store),
                            contentDescription = "IC_SHOP",
                            tint = OnjungTheme.colors.main_coral
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "가게명",
                            style = OnjungTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = OnjungTheme.colors.text_1
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_gps),
                            contentDescription = "IC_GPS",
                            tint = OnjungTheme.colors.main_coral
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "주소",
                            style = OnjungTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = OnjungTheme.colors.text_1
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_calendar),
                            contentDescription = "IC_CALENDAR",
                            tint = OnjungTheme.colors.main_coral
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "방문일",
                            style = OnjungTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = OnjungTheme.colors.text_1
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_receipt_chip),
                            contentDescription = "IC_RECEIPT",
                            tint = OnjungTheme.colors.main_coral
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "사용 금액",
                            style = OnjungTheme.typography.body1.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = OnjungTheme.colors.text_1
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = name,
                            style = OnjungTheme.typography.body1,
                            color = OnjungTheme.colors.text_2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = address,
                            style = OnjungTheme.typography.body1,
                            color = OnjungTheme.colors.text_2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = visitDate,
                            style = OnjungTheme.typography.body1,
                            color = OnjungTheme.colors.text_2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "$spentAmount 원",
                            style = OnjungTheme.typography.body1,
                            color = OnjungTheme.colors.text_2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionButton(
                    text = "다시 촬영",
                    modifier = Modifier.weight(1f),
                    onClick = hideBottomSheet,
                    backgroundColor = OnjungTheme.colors.gray_2,
                    contentColor = OnjungTheme.colors.text_3
                )

                ActionButton(
                    text = "등록하기",
                    modifier = Modifier.weight(1f),
                    onClick = { onConfirm() },
                    backgroundColor = OnjungTheme.colors.main_coral,
                    contentColor = OnjungTheme.colors.white
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = OnjungTheme.typography.h2.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Preview
@Composable
fun ReceiptConfirmBottomSheetPreview() {
    OnjungTheme {
        ReceiptConfirmBottomSheet(
            name = "가게명",
            address = "주소",
            visitDate = "방문일",
            spentAmount = "10000",
            onConfirm = { },
            hideBottomSheet = { }
        )
    }
}
