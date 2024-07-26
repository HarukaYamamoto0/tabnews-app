package com.harukadev.tabnews.ui.components.fragments

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
import com.harukadev.tabnews.data.fakeData
import com.harukadev.tabnews.ui.components.activitys.NavigationItem
import com.harukadev.tabnews.ui.components.items.PostItem
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

@Composable
fun RelevantPostsFragment(navController: NavHostController) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        runBlocking {
            val api = TabNewsApi()
            val posts = api.getPost(1, 30, TabNewsApi.PostStrategy.RELEVANT)
            itemsIndexed(posts) { index, post ->
                PostItem(index + 1, post, onClick = {
                    val postJson = Json.encodeToString(post)
                    val encodedPostJson = URLEncoder.encode(postJson, "UTF-8")
                    navController.navigate("postContent/$encodedPostJson")
                })
            }
            api.close()
        }
    }
}

@Preview
@Composable
fun PreviewRelevantPostsFragment() {
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
