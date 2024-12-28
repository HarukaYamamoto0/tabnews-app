package com.harukadev.tabnews.posts.presentation.relevant_posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harukadev.tabnews.core.domain.onError
import com.harukadev.tabnews.core.domain.onSuccess
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.domain.Strategy
import com.harukadev.tabnews.posts.presentation.models.toPostUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RelevantPostsViewModel(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RelevantPostsState())
    val state = _state
        .onStart { loadPosts() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500L),
            RelevantPostsState()
        )

    private val _events = Channel<RelevantPostsEvent>()
    val events = _events.receiveAsFlow()

    private fun loadPosts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            postRepository
                .getPosts(page = 1, perPage = 60, Strategy.RELEVANT)
                .onSuccess { posts ->
                    _state.update {
                        it.copy(
                            posts = posts.map { post -> post.toPostUi() },
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(RelevantPostsEvent.Error(error))
                }
        }
    }
}