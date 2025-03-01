package com.harukadev.tabnews.settings.presentation.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.settings.presentation.components.SettingsCategory
import com.harukadev.tabnews.settings.presentation.components.SettingsSwitch
import com.harukadev.tabnews.settings.presentation.components.SettingsTextOpen
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        SettingsCategory(
            title = R.string.settings_category_profile
        ) {
            SettingsTextOpen(
                title = R.string.settings_option_profile_management
            )
            SettingsTextOpen(
                title = R.string.settings_option_profile_security,
                enabled = false
            )
        }

        SettingsCategory(
            title = R.string.settings_category_appearance
        ) {
            SettingsTextOpen(
                title = R.string.settings_option_appearance_theme
            )
            SettingsTextOpen(
                title = R.string.settings_option_appearance_reading,
                enabled = false
            )
        }

        SettingsCategory(
            title = R.string.settings_category_notifications
        ) {
            SettingsSwitch(
                title = R.string.settings_option_notifications_new_posts,
                enabled = false
            )
            SettingsSwitch(
                title = R.string.settings_option_notifications_comments,
                enabled = false
            )
        }

        SettingsCategory(
            title = R.string.settings_category_data_sync
        ) {
            SettingsTextOpen(
                title = R.string.settings_option_data_sync_manual,
                enabled = false
            )
            SettingsSwitch(
                title = R.string.settings_option_data_sync_auto,
                enabled = false
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen()
    }
}
