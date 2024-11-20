package com.daon.onjung.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.daon.onjung.R
import com.daon.onjung.ui.component.button.FilledWidthButton
import com.daon.onjung.ui.theme.OnjungTheme

@Composable
fun OnjungSuccessDialog(
    title: String,
    description: String,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest
    ) {
        Box(
            modifier = Modifier.background(
                color = OnjungTheme.colors.white,
                shape = RoundedCornerShape(14.dp)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(
                        top = 10.dp, bottom = 10.dp,
                        start = 20.dp, end = 20.dp
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        title,
                        style = OnjungTheme.typography.h2.copy(
                            fontWeight = FontWeight.Bold,
                            color = OnjungTheme.colors.text_1
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        description,
                        style = OnjungTheme.typography.body2.copy(
                            color = OnjungTheme.colors.text_2
                        )
                    )
                }

                Image(
                    painter = painterResource(id = R.mipmap.img_post_receipt_success),
                    contentDescription = "IC_RECEIPT_SUCCESS",
                    modifier = Modifier
                )

                FilledWidthButton(
                    text = "확인",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                ) {
                    onDismissRequest()
                }
            }
        }
    }
}

@Preview
@Composable
fun PostReceiptSuccessDialogPreview() {
    OnjungTheme {
        OnjungSuccessDialog(
            onDismissRequest = {},
            title = "온기를 실천해 주셔서 감사해요",
            description = "온정이 계속 함께할게요!"
        )
    }
}