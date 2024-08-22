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
import com.harukadev.tabnews.ui.components.components.Screen
//import com.harukadev.tabnews.ui.components.activitys.PostContentNavigationItem
import com.harukadev.tabnews.ui.components.items.PostItem
import com.harukadev.tabnews.utils.fakeData.fakeData
import kotlinx.coroutines.runBlocking

@Composable
fun RelevantPostsScreen(navController: NavHostController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        runBlocking {
            val api = TabNewsApi()
            val posts = api.getPost(1, 20, TabNewsApi.PostStrategy.RELEVANT)
            itemsIndexed(posts) { index, post ->
                PostItem(index + 1, post, onClick = {
                    navController.navigate(Screen.PostContent.route)
                })
            }
            api.close()
        }
    }
}

@Preview
@Composable
fun RelevantPostsPreview() {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        itemsIndexed(fakeData) { index, post ->
            PostItem(index + 1, post)
        }
    }
}
