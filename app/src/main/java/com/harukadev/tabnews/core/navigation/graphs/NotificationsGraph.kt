package com.harukadev.tabnews.core.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.harukadev.tabnews.notifications.presentation.NotificationScreen
import kotlinx.serialization.Serializable

@Serializable
object NotificationsGraph

@Serializable
object NotificationRouter

fun NavGraphBuilder.notificationsGraph(navController: NavController) {
    navigation<NotificationsGraph>(
        startDestination = NotificationRouter
    ) {
        composable<NotificationRouter> {
            NotificationScreen()
        }
    }
}