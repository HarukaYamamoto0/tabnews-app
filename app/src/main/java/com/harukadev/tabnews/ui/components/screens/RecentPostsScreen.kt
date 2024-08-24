package com.harukadev.tabnews.ui.components.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.harukadev.tabnews.api.TabNewsApi
import com.harukadev.tabnews.ui.components.components.PostContentRoute
import com.harukadev.tabnews.ui.components.items.PostItem
import com.harukadev.tabnews.ui.theme.Dimens
import kotlinx.coroutines.runBlocking

@Composable
fun RecentPostsScreen(navController: NavHostController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(Dimens.paddingFeed)
    ) {
        runBlocking {
            val api = TabNewsApi()
            val posts = api.getPost(1, 20, TabNewsApi.ContentStrategy.NEW)
            itemsIndexed(posts) { index, post ->
                PostItem(index + 1, post, onClick = {
                    navController.navigate(
                        PostContentRoute(
                            ownerUsername = post.ownerUsername,
                            slug = post.slug
                        )
                    )
                })
            }
            api.close()
        }
    }
}

