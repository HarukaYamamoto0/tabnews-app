package com.harukadev.tabnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.harukadev.tabnews.notifications.presentation.NotificationScreen
import com.harukadev.tabnews.posts.presentation.content_post.PostContentScreen
import com.harukadev.tabnews.posts.presentation.content_post.PostContentViewModel
import com.harukadev.tabnews.posts.presentation.recent_posts.RecentPostsScreen
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsScreen
import com.harukadev.tabnews.settings.presentation.settings_screen.SettingsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed interface Router

@Immutable
@Serializable
data class PostContentRouter(
    val ownerUsername: String,
    val slug: String
) : Router

@Immutable
@Serializable
data object RecentPostsRouter : Router

@Immutable
@Serializable
data object RelevantPostsRouter : Router

@Immutable
@Serializable
data object NotificationRouter : Router

@Immutable
@Serializable
data object SettingsRouter : Router

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RecentPostsRouter
    ) {
        composable<PostContentRouter> {
            val args = it.toRoute<PostContentRouter>()

            val viewModel: PostContentViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(args) {
                viewModel.loadPost(ownerUsername = args.ownerUsername, slug = args.slug)
            }

            PostContentScreen(state = state)
        }
        composable<RelevantPostsRouter> {
            RelevantPostsScreen(onPostSelected = { post ->
                navController.navigate(
                    PostContentRouter(
                        ownerUsername = post.ownerUsername, slug = post.slug
                    )
                )
            })
        }
        composable<RecentPostsRouter> {
            RecentPostsScreen(onPostSelected = { post ->
                navController.navigate(
                    PostContentRouter(
                        ownerUsername = post.ownerUsername, slug = post.slug
                    )
                )
            })
        }
        composable<NotificationRouter> {
            NotificationScreen()
        }

        composable<SettingsRouter> {
            SettingsScreen()
        }
    }
}