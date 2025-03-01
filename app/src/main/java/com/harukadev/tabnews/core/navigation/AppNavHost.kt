package com.harukadev.tabnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.harukadev.tabnews.core.navigation.graphs.PostsGraph
import com.harukadev.tabnews.core.navigation.graphs.notificationsGraph
import com.harukadev.tabnews.core.navigation.graphs.postsGraph
import com.harukadev.tabnews.core.navigation.graphs.settingsGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PostsGraph
    ) {
        postsGraph(navController)
        notificationsGraph(navController)
        settingsGraph(navController)
    }
}