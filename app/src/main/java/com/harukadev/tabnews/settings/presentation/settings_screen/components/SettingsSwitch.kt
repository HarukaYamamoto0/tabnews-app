package com.harukadev.tabnews.settings.presentation.settings_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.textDark
import com.harukadev.tabnews.ui.theme.textLight

@Composable
fun SettingsSwitch(
    modifier: Modifier = Modifier,
    title: Int,
    checked: Boolean = true,
    enabled: Boolean = true,
    onCheckedChange: (value: Boolean) -> Unit = {}
) {
    val background =
        if (enabled)
            MaterialTheme.colorScheme.onBackground
        else
            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    val contentColor = if (isSystemInDarkTheme()) textLight else textDark
    val finalContentColor = if (enabled) contentColor else contentColor.copy(alpha = 0.5f)
    var checkedRemember by remember { mutableStateOf(checked) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background)
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 0.2.dp.toPx()
                )
            }
            .clickable(
                enabled = enabled,
                onClick = {
                    checkedRemember = !checkedRemember
                    onCheckedChange(!checkedRemember)
                }
            )
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 13.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(title),
            style = TextStyle(color = finalContentColor)
        )
        Switch(
            checked = checkedRemember,
            enabled = enabled,
            onCheckedChange = { value ->
                checkedRemember = value
                onCheckedChange(value)
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun SettingsSwitchPreview() {
    SettingsSwitch(
        title = R.string.settings_option_your_profile
    )
}
