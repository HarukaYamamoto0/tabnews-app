package com.harukadev.tabnews.posts.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun PostItemInfoText(
    text: String,
    modifier: Modifier = Modifier
) {
    val defaultTextStyleOfInfos = LocalTextStyle.current.copy(
        lineBreak = LineBreak.Simple,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    Text(
        text = text,
        style = defaultTextStyleOfInfos,
        overflow = TextOverflow.Ellipsis,
    )
}

@Preview(showBackground = true)
@Composable
private fun PostItemTextInfoPreview(modifier: Modifier = Modifier) {
    AppTheme {
        PostItemInfoText(
            text = "150 comments"
        )
    }
}