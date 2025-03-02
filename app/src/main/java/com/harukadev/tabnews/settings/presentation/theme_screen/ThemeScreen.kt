package com.harukadev.tabnews.settings.presentation.theme_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.settings.presentation.components.IconTextButton
import com.harukadev.tabnews.settings.presentation.theme_screen.components.ColorPaletteButton
import com.harukadev.tabnews.ui.theme.AppTheme
import com.harukadev.tabnews.ui.theme.textDark
import com.harukadev.tabnews.ui.theme.textLight

@Composable
fun ThemeScreen(
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) textLight else textDark

    var selectedTheme by remember { mutableStateOf("System") }
    var selectedPalette by remember { mutableStateOf("Default") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        ThemeSection(
            selectedTheme = selectedTheme,
            onThemeSelected = { selectedTheme = it },
            contentColor = contentColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        ColorPaletteSection(
            selectedPalette = selectedPalette,
            onPaletteSelected = { selectedPalette = it },
            contentColor = contentColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        ResetSection(contentColor = contentColor)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ThemeSection(
    selectedTheme: String,
    onThemeSelected: (String) -> Unit,
    contentColor: Color
) {
    Column {
        SectionTitle(
            title = "Color Mode",
            description = "Choose what the app's theme should be, or just let it be system-based",
            contentColor = contentColor
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ThemeOption(
                icon = R.drawable.sun,
                text = "Light mode",
                isSelected = selectedTheme == "Light",
                onClick = { onThemeSelected("Light") },
                contentColor = contentColor
            )
            ThemeOption(
                icon = R.drawable.moon,
                text = "Dark mode",
                isSelected = selectedTheme == "Dark",
                onClick = { onThemeSelected("Dark") },
                contentColor = contentColor
            )
            ThemeOption(
                icon = R.drawable.monitor,
                text = "System",
                isSelected = selectedTheme == "System",
                onClick = { onThemeSelected("System") },
                contentColor = contentColor
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColorPaletteSection(
    selectedPalette: String,
    onPaletteSelected: (String) -> Unit,
    contentColor: Color
) {
    Column {
        SectionTitle(
            title = "Main Colors",
            description = "Choose the main colors of the app or just leave the default ones",
            contentColor = contentColor
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf("Default", "Vibrant", "Pastel", "Monochrome").forEach { palette ->
                ColorPaletteButton(
                    palette = when (palette) {
                        "Default" -> Pair(Color(0xFF6750A4), Color(0xFFEADDFF))
                        "Vibrant" -> Pair(Color(0xFFFF6F61), Color(0xFF6B5B95))
                        "Pastel" -> Pair(Color(0xFFA2D5F2), Color(0xFFF2A2A2))
                        else -> Pair(Color(0xFF333333), Color(0xFF666666))
                    },
                    name = palette,
                    selected = selectedPalette == palette,
                    onClick = { onPaletteSelected(palette) }
                )
            }
        }
    }
}

@Composable
private fun ResetSection(contentColor: Color) {
    Column {
        SectionTitle(
            title = "Reset",
            description = "Reset the colors as they came into the world",
            contentColor = contentColor
        )

        IconTextButton(
            icon = R.drawable.trash_fill,
            text = "Reset to default",
            iconTint = contentColor,
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
            onClick = { /* Handle reset */ }
        )
    }
}

@Composable
private fun SectionTitle(title: String, description: String, contentColor: Color) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(
            text = title,
            color = contentColor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
            color = contentColor,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ThemeOption(
    icon: Int,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    contentColor: Color
) {
    IconTextButton(
        icon = icon,
        text = text,
        background = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else contentColor
        ),
        iconTint = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else contentColor,
        contentDescription = text,
        onClick = onClick
    )
}

@PreviewLightDark
@Composable
private fun ThemeScreenPreview() {
    AppTheme {
        ThemeScreen()
    }
}