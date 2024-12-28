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
import com.harukadev.tabnews.posts.presentation.content_post.PostContentScreen
import com.harukadev.tabnews.posts.presentation.content_post.PostContentViewModel
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Immutable
@Serializable
data object RelevantPostsRouter

@Immutable
@Serializable
data class PostContentRouter(
    val ownerUsername: String,
    val slug: String
)

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RelevantPostsRouter
    ) {
        composable<RelevantPostsRouter> {
            RelevantPostsScreen(
                onPostSelected = { post ->
                    navController.navigate(
                        PostContentRouter(
                            ownerUsername = post.ownerUsername,
                            slug = post.slug
                        )
                    )
                }
            )
        }
        composable<PostContentRouter> {
            val args = it.toRoute<PostContentRouter>()

            val viewModel: PostContentViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(args) {
                viewModel.loadPost(ownerUsername = args.ownerUsername, slug = args.slug)
            }

            PostContentScreen(state = state)
        }
    }
}