package com.harukadev.tabnews.posts.presentation.content_post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun PostContentHeader(
    modifier: Modifier = Modifier,
    username: String,
    readTime: String = "15 min read",
    createdAt: String,
    onClick: () -> Unit = {}
) {
    val contentColor = MaterialTheme.colorScheme.onTertiary

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val divider = stringResource(R.string.divider)

        Text(
            text = username,
            fontSize = 13.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                    MaterialTheme.colorScheme.secondary.copy(alpha = .2f)
                )
                .clickable { onClick() }
                .padding(horizontal = 6.dp, vertical = 1.5.dp)
        )

        Spacer(Modifier.size(8.dp))

        Text(
            text = readTime + divider,
            color = contentColor,
            fontSize = 12.sp,
        )
        Text(
            text = createdAt,
            color = contentColor,
            fontSize = 12.sp,
        )
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun PostContentHeaderPreview() {
    AppTheme {
        PostContentHeader(
            username = "HarukaYamamoto0",
            createdAt = "15 hours ago"
        )
    }
}