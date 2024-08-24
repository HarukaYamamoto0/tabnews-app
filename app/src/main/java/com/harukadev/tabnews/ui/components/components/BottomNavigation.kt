package com.harukadev.tabnews.ui.components.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItemColors
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
import com.harukadev.tabnews.ui.theme.Dimens
import kotlinx.serialization.Serializable

@Serializable
data class Screen(
    val name: Int,
    val iconIdActive: Int,
    val iconIdInactive: Int
) {
    val route: String
        get() = name.toString()
}

val HomeRoute = Screen(
    R.string.home,
    R.drawable.ic_home_active,
    R.drawable.ic_home_inactive
)
val RecentPostsRoute = Screen(
    R.string.recentPosts,
    R.drawable.ic_schedule_active,
    R.drawable.ic_schedule_inactive
)
val NotificationsRoute = Screen(
    R.string.notifications,
    R.drawable.ic_notifications_active,
    R.drawable.ic_notifications_inactive
)
val SettingsRoute = Screen(
    R.string.settings,
    R.drawable.ic_manage_accounts_active,
    R.drawable.ic_manage_accounts_inactive
)

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

    BottomAppBar(
        modifier = Modifier.height(Dimens.heightBottomNavigation),
        containerColor = Colors.onBackground,
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                interactionSource = MutableInteractionSource(),
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = Colors.transparent,
                    selectedIconColor = Colors.transparent,
                    selectedTextColor = Colors.transparent,
                    disabledIconColor = Colors.transparent,
                    disabledTextColor = Colors.transparent,
                    unselectedIconColor = Colors.transparent,
                    unselectedTextColor = Colors.transparent,
                ),
                modifier = Modifier.height(Dimens.heightBottomNavigation),
                icon = {
                    IconItem(
                        iconId = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) screen.iconIdActive else screen.iconIdInactive,
                        contentDescription = stringResource(id = screen.name)
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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