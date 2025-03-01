package com.harukadev.tabnews.core.navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.harukadev.tabnews.posts.presentation.content_post.PostContentScreen
import com.harukadev.tabnews.posts.presentation.content_post.PostContentViewModel
import com.harukadev.tabnews.posts.presentation.recent_posts.RecentPostsScreen
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object PostsGraph

@Serializable
data class PostContentRouter(
    val ownerUsername: String, val slug: String
)

@Serializable
data object RecentPostsRouter

@Serializable
data object RelevantPostsRouter

fun NavGraphBuilder.postsGraph(navController: NavController) {
    navigation<PostsGraph>(
        startDestination = RecentPostsRouter
    ) {
        composable<RecentPostsRouter> {
            RecentPostsScreen(onPostSelected = { post ->
                navController.navigate(
                    PostContentRouter(
                        ownerUsername = post.ownerUsername, slug = post.slug
                    )
                )
            })
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
        composable<PostContentRouter> {
            val args = it.toRoute<PostContentRouter>()
            val viewModel: PostContentViewModel = koinViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(args) {
                viewModel.loadPost(
                    ownerUsername = args.ownerUsername, slug = args.slug
                )
            }

            PostContentScreen(state = state)
        }
    }
}
