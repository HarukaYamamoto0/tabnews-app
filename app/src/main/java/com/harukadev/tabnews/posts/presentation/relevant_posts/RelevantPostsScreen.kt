package com.harukadev.tabnews.posts.presentation.relevant_posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harukadev.tabnews.posts.presentation.components.NoPosts
import com.harukadev.tabnews.posts.presentation.components.PostItem
import com.harukadev.tabnews.posts.presentation.components.previewPostUi

@Composable
fun RelevantPostsScreen(
    state: RelevantPostsState,
    modifier: Modifier = Modifier
        .background(MaterialTheme.colorScheme.background),
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (state.posts.isNotEmpty()) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                itemsIndexed(state.posts) { index, post ->
                    PostItem(
                        position = index + 1,
                        post = post
                    )
                }
            }
        } else {
            NoPosts()
        }
    }
}

@Preview
@Composable
private fun RelevantPostsScreenPreview() {
    RelevantPostsScreen(
        state = RelevantPostsState(
            isLoading = false,
            posts = listOf(previewPostUi)
        )
    )
}