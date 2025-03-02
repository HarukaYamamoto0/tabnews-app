package com.harukadev.tabnews.core.navigation.graphs

import androidx.compose.runtime.Immutable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.harukadev.tabnews.settings.presentation.settings_screen.SettingsScreen
import com.harukadev.tabnews.settings.presentation.theme_screen.ThemeScreen
import kotlinx.serialization.Serializable

@Immutable
@Serializable
object SettingsGraph

@Immutable
@Serializable
object SettingsRouter

@Immutable
@Serializable
object ThemeRouter

fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<SettingsGraph>(
        startDestination = SettingsRouter
    ) {
        composable<SettingsRouter> {
            SettingsScreen(navController)
        }
        composable<ThemeRouter> {
            ThemeScreen()
        }
    }
}