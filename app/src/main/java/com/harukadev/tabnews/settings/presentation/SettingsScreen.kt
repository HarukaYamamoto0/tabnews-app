package com.harukadev.tabnews.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.harukadev.tabnews.R
import com.harukadev.tabnews.posts.presentation.components.MessageScreen

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    MessageScreen(
        icon = R.drawable.user_circle_gear_fill,
        title = R.string.sorry,
        message = R.string.this_part_of_the_app_is_still_under_construction
    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}