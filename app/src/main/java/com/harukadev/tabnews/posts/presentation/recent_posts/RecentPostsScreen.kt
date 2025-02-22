package com.harukadev.tabnews.posts.presentation.recent_posts

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.tabnews.R
import com.harukadev.tabnews.core.presentation.ObserverAsEvents
import com.harukadev.tabnews.core.presentation.toString
import com.harukadev.tabnews.posts.presentation.components.MessageScreen
import com.harukadev.tabnews.posts.presentation.components.PostItem
import com.harukadev.tabnews.posts.presentation.models.CardPostUi
import com.harukadev.tabnews.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecentPostsScreen(
    modifier: Modifier = Modifier,
    onPostSelected: (CardPostUi) -> Unit = {}
) {
    val viewModel: RecentPostsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserverAsEvents(viewModel.events) { event ->
        when (event) {
            is RecentPostsEvent.Error -> {
                Toast.makeText(
                    context, event.error.toString(context), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    if (state.isLoading) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (state.posts.isNotEmpty()) {
            LazyColumn(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.tertiary)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(state.posts) { index, post ->
                    PostItem(position = index + 1, post = post, onClick = {
                        onPostSelected(post)
                    })
                }
            }
        } else {
            MessageScreen(
                icon = R.drawable.forest,
                title = R.string.no_content_found,
                message = R.string.when_i_arrived
            )
        }
    }
}

@Preview
@Composable
private fun RelevantPostsScreenPreview() {
    AppTheme {
        RecentPostsScreen()
    }
}