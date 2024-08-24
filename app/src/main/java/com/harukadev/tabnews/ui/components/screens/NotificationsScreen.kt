package com.harukadev.tabnews.ui.components.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.ui.theme.Colors

@Composable
private fun NotificationsScreenRaw(navController: NavHostController) {
    Text(text = "Notifications", style = TextStyle(color = Colors.text))
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    NotificationsScreenRaw(navController = navController)
}

@Preview
@Composable
private fun NotificationsScreenPreview() {
    NotificationsScreenRaw(navController = rememberNavController())
}
