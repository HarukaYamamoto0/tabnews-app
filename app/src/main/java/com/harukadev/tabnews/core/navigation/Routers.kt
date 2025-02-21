package com.harukadev.tabnews.core.navigation

import androidx.compose.runtime.Immutable
import com.harukadev.tabnews.R
import kotlinx.serialization.Serializable

sealed interface Router

@Immutable
@Serializable
data object RelevantPostsRouter : Router

@Immutable
@Serializable
data object RecentPostsRouter : Router

@Immutable
@Serializable
data class PostContentRouter(
    val ownerUsername: String,
    val slug: String
) : Router

data class BottomNavigationItem(
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val router: Router
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = R.string.recents,
        selectedIcon = R.drawable.house_fill,
        unselectedIcon = R.drawable.house,
        hasNews = false,
        badgeCount = null,
        RecentPostsRouter
    ),
    BottomNavigationItem(
        title = R.string.relevant,
        selectedIcon = R.drawable.ranking_fill,
        unselectedIcon = R.drawable.ranking,
        hasNews = false,
        badgeCount = null,
        router = RelevantPostsRouter
    ),
    BottomNavigationItem(
        title = R.string.notifications,
        selectedIcon = R.drawable.bell_fill,
        unselectedIcon = R.drawable.bell,
        hasNews = false,
        badgeCount = null,
        router = RelevantPostsRouter
    ),
    BottomNavigationItem(
        title = R.string.settings,
        selectedIcon = R.drawable.user_circle_gear_fill,
        unselectedIcon = R.drawable.user_circle_gear,
        hasNews = false,
        badgeCount = null,
        router = RelevantPostsRouter
    )
)