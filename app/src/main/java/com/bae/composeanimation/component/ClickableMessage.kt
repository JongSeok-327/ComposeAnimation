package com.bae.composeanimation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ClickableMessage(message: Message) {
    val showDetails = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(16.dp)
            .clip(RoundedCornerShape(20.dp)) // 모서리 둥글게 깎기
            .background(Color.White) // 배경색 설정
            .padding(16.dp) // 내부 패딩 설정
    ) {
        ClickableText(
            text = message.content,
            onClick = {
                showDetails.value = !showDetails.value
            }
        )

//        if (showDetails.value) {
//            Text(
//                text = message.timestamp.toString()
//            )
//        }
        // 정말 간단한 예제 1.(AnimatedVisibility 이용)
        AnimatedVisibility(visible = showDetails.value) {
            Text(
                text = message.timestamp.toString()
            )
        }
    }
}

data class Message(
    val content: AnnotatedString,
    val timestamp: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClickableMessagePreview() {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Magenta,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
        ) {
            append("This is a Title")
        }
        append("\n")
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        ) {
            append("Content Text")
        }
        append("\n")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
            )
        ) {
            append("show more....")
        }
    }

    val currentTime = LocalTime.now()
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    val formattedTime = currentTime.format(formatter)

    ClickableMessage(
        message = Message(
            content = annotatedText,
            timestamp = formattedTime
        )
    )
}