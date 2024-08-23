package com.harukadev.tabnews.ui.components.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.Colors
import kotlinx.serialization.Serializable

@Serializable
data class Screen(
    val name: Int,
    val iconId: Int
) {
    val route: String
        get() = name.toString()
}

val HomeRoute = Screen(R.string.home, R.drawable.ic_home)
val RecentPostsRoute = Screen(R.string.recentPosts, R.drawable.ic_recent)
val NotificationsRoute = Screen(R.string.notifications, R.drawable.ic_notifications)
val SettingsRoute = Screen(R.string.settings, R.drawable.ic_manage_accounts)

@Serializable
data class PostContentRoute(val ownerUsername: String, val slug: String)


val items = listOf(
    HomeRoute,
    RecentPostsRoute,
    NotificationsRoute,
    SettingsRoute,
)

@Composable
fun IconItem(@DrawableRes iconId: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription,
        modifier = Modifier.size(24.dp, 24.dp)
    )
}

@Preview
@Composable
fun BottomNavigationCustom(navController: NavController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(modifier = Modifier.height(64.dp), containerColor = Colors.onBackground) {
        items.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.height(64.dp),
                selectedContentColor = Colors.transparent,
                icon = {
                    IconItem(
                        iconId = screen.iconId,
                        contentDescription = stringResource(id = screen.name)
                    )
                },
                // TODO: putting labels in BottomNavigation
                // label = { Text(stringResource(screen.name), color = Colors.text) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.name.toString() } == true,
                onClick = {
                    navController.navigate(screen.name.toString()) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}