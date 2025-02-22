package com.harukadev.tabnews.posts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme
import com.harukadev.tabnews.ui.theme.darkGreen

@Composable
fun MessageScreen(
    modifier: Modifier = Modifier,
    icon: Int,
    title: Int,
    message: Int,
    iconModifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    messageModifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White
    else Color.Black

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = stringResource(title),
            modifier = iconModifier
                .padding(bottom = 15.dp)
                .size(75.dp),
            tint = darkGreen
        )
        Text(
            text = stringResource(title), style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
                textAlign = TextAlign.Center
            ), modifier = titleModifier.padding(bottom = 5.dp)
        )
        Text(
            text = stringResource(message), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = contentColor.copy(alpha = .5f),
                textAlign = TextAlign.Center
            ), modifier = messageModifier
        )
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun MessageScreenPreview() {
    AppTheme {
        MessageScreen(
            title = R.string.no_content_found,
            icon = R.drawable.forest,
            message = R.string.post_not_found
        )
    }
}