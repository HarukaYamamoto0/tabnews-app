package com.harukadev.tabnews.notifications.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.harukadev.tabnews.R
import com.harukadev.tabnews.posts.presentation.components.MessageScreen

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier
) {
    MessageScreen(
        icon = R.drawable.bell_fill,
        title = R.string.sorry,
        message = R.string.this_part_of_the_app_is_still_under_construction
    )
}

@Preview
@Composable
private fun NotificationScreenPreview() {
    NotificationScreen()
}