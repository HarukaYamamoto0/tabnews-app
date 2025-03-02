package com.harukadev.tabnews.settings.presentation.theme_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.harukadev.tabnews.ui.theme.AppTheme
import com.harukadev.tabnews.ui.theme.accentColor
import com.harukadev.tabnews.ui.theme.primaryDark

@Composable
fun ColorPaletteButton(
    modifier: Modifier = Modifier,
    palette: Pair<Color, Color>,
    name: String,
    onClick: () -> Unit = {},
    selected: Boolean = false
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .clickable(onClick = onClick)
            .border(
                width = 0.5.dp,
                color = if (selected) accentColor else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 10.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Transparent)
                .rotate(50f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .padding(end = 12.dp)
                    .background(palette.first)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .padding(start = 12.dp)
                    .background(palette.second)
            )
        }

        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@PreviewLightDark
@Composable
private fun ColorPaletteButtonPreview() {
    AppTheme {
        ColorPaletteButton(
            palette = Pair(accentColor, primaryDark), name = "Default", selected = true
        )
    }
}