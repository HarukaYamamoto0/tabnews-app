package com.harukadev.tabnews.ui.components.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.harukadev.tabnews.api.TabNewsApi
import com.harukadev.tabnews.ui.components.items.PostItem
import kotlinx.coroutines.runBlocking

@Composable
fun RecentPostsScreen(navController: NavHostController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        runBlocking {
            val api = TabNewsApi()
            val posts = api.getPost(1, 20, TabNewsApi.ContentStrategy.NEW)
            itemsIndexed(posts) { index, post ->
                PostItem(index + 1, post, onClick = {
//                    Screen.RecentPosts.data = arrayOf(object {
//                        val author = post.author
//                        val slug = post.slug
//                    })
//                    navController.navigate(Screen.RecentPosts)
                })
            }
            api.close()
        }
    }
}

