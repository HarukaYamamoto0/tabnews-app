package com.harukadev.tabnews.ui.components.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.harukadev.tabnews.ui.theme.Colors

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Text(text = "Notifications", style = TextStyle(color = Colors.text))
}