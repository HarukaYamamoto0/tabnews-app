package com.harukadev.tabnews.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme
import com.harukadev.tabnews.ui.theme.textDark
import com.harukadev.tabnews.ui.theme.textLight

@Composable
fun SettingsCategory(
    modifier: Modifier = Modifier,
    title: Int,
    content: @Composable () -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) textLight else textDark

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(Color.Transparent)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Text(
            text = stringResource(title),
            modifier = Modifier.padding(bottom = 10.dp),
            style = TextStyle(
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onBackground)
        ) { content() }
    }
}

@PreviewLightDark
@Composable
private fun SettingsCategoryPreview() {
    AppTheme {
        SettingsCategory(
            title = R.string.test_title_categoty,
        ) {
            SettingsTextOpen(title = R.string.test_title)
            SettingsTextOpen(title = R.string.test_title)
            SettingsTextOpen(title = R.string.test_title)
            SettingsTextOpen(title = R.string.test_title)
            SettingsTextOpen(title = R.string.test_title)
        }
    }
}