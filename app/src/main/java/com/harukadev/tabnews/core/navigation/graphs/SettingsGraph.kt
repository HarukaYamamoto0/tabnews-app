package com.harukadev.tabnews.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.harukadev.tabnews.settings.presentation.settings_screen.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRouter

@Serializable
object SettingsGraph

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<SettingsGraph>(
        startDestination = SettingsRouter
    ) {
        composable<SettingsRouter> {
            SettingsScreen()
        }
    }
}