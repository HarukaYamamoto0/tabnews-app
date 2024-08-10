package com.harukadev.tabnews.ui.components.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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

sealed class Screen(val route: String, @StringRes val name: Int, @DrawableRes val iconId: Int) {
    data object Home :
        Screen("Home", R.string.home, R.drawable.ic_home)

    data object RecentPosts :
        Screen("RecentPosts", R.string.recentPosts, R.drawable.ic_recent)

    data object Notifications :
        Screen("Notifications", R.string.notifications, R.drawable.ic_notifications)

    data object Settings :
        Screen("Settings", R.string.settings, R.drawable.ic_manage_accounts)
}

val items = listOf(
    Screen.Home,
    Screen.RecentPosts,
    Screen.Notifications,
    Screen.Settings,
)

@Composable
fun IconItem(@DrawableRes iconId: Int, contentDescription: String) {
    Image(painter = painterResource(id = iconId), contentDescription)
}

@Preview
@Composable
fun BottomNavigationCustom(navController: NavController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(modifier = Modifier.height(50.dp), containerColor = Colors.onBackground) {
        items.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.height(50.dp),
                icon = { IconItem(iconId = screen.iconId, contentDescription = stringResource(id = screen.name)) },
//                label = { Text(stringResource(screen.name), color = Colors.text) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
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