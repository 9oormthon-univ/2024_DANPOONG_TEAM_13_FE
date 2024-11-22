package com.daon.onjung.ui.profile.component

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daon.onjung.R
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
internal fun MealTicketBottomSheet(
    qrCodeImgUrl: String,
    name: String,
    address: String,
    expirationDate: String
) {
    val captureController = rememberCaptureController()
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    Box(
        modifier = Modifier.capturable(
            captureController
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFFFEEDF),
                            Color.White,
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    ),
                    shape = RoundedCornerShape(
                        topStart = 26.dp,
                        topEnd = 26.dp
                    )
                )
        ) {
            Icon(
                modifier = Modifier
                    .width(49.dp)
                    .align(Alignment.TopEnd)
                    .padding(end = 20.dp, top = 20.dp),
                painter = painterResource(id = R.drawable.ic_app_logo),
                contentDescription = "IC_APP_LOGO",
                tint = Color.Unspecified
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    "1만원 금액권",
                    style = OnjungTheme.typography.h2.copy(
                        fontWeight = FontWeight.Bold,
                        color = OnjungTheme.colors.text_1
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "사장님께 QR코드를 보여주세요!",
                    style = OnjungTheme.typography.body1.copy(
                        color = Color(0xFF888B8F)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                val bitmap = decodeBase64ToBitmap(qrCodeImgUrl)

                Box(
                    modifier = Modifier
                        .background(OnjungTheme.colors.white)
                        .padding(6.dp),
                ) {
                    bitmap?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "IC_DUMMY_QR_CODE",
                            modifier = Modifier.size(166.dp)
                        )
                    } ?: Icon(
                        modifier = Modifier.size(166.dp),
                        painter = painterResource(id = R.drawable.ic_dummy_qr_code),
                        contentDescription = "IC_QR_CODE",
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                MealTicketSummary(
                    name = name,
                    address = address,
                    expirationDate = expirationDate
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilledWidthButton(
                    text = "사진으로 저장하기",
                    modifier = Modifier
                        .padding(
                            bottom = 40.dp
                        )
                ) {
                    scope.launch {
                        val bitmapAsync = captureController.captureAsync()
                        try {
                            val bitmap = bitmapAsync.await()
                            saveBitmapToGallery(context, bitmap.asAndroidBitmap())
                            Toast.makeText(
                                context,
                                "성공! 앨범에 저장했어요 \uD83D\uDE06",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (error: Throwable) {
                            Toast.makeText(context, "사진 저장에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MealTicketSummary(
    name: String,
    address: String,
    expirationDate: String
) {
    Box(
        modifier = Modifier.background(
            color = OnjungTheme.colors.gray_3,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
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
                        text = "위치",
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
                        text = "유효기간",
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
                    modifier = Modifier.fillMaxWidth().height(24.dp),
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
                    modifier = Modifier.fillMaxWidth().height(24.dp),
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
                    modifier = Modifier.fillMaxWidth().height(24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = expirationDate,
                        style = OnjungTheme.typography.body1,
                        color = OnjungTheme.colors.text_2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun saveBitmapToGallery(context: Context, bitmap: Bitmap) {
    val filename = "meal_ticket_${System.currentTimeMillis()}.png"
    val fos: OutputStream?

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/Onjung")
        }
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fos = imageUri?.let { resolver.openOutputStream(it) }
    } else {
        val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val image = File(imagesDir, filename)
        fos = FileOutputStream(image)
    }

    fos?.use {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
    }
}


@Preview
@Composable
internal fun MealTicket() {
    MealTicketBottomSheet(
        qrCodeImgUrl = "",
        name = "한걸음 닭꼬치",
        address = "송파구 오금로 533 1층 (거여동)",
        expirationDate = "2024년 11월 30일"
    )
}

private fun decodeBase64ToBitmap(base64String: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }
}