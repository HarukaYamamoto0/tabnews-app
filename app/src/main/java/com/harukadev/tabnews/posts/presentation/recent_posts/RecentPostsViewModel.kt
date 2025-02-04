package com.harukadev.tabnews.posts.presentation.recent_posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harukadev.tabnews.core.domain.onError
import com.harukadev.tabnews.core.domain.onSuccess
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.domain.Strategy
import com.harukadev.tabnews.posts.presentation.models.toCardPostUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecentPostsViewModel(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _state = MutableStateFlow(RecentPostsState())
    val state = _state
        .onStart { loadPosts() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(30000L),
            RecentPostsState()
        )

    private val _events = Channel<RecentPostsEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: RecentPostAction) {
        when (action) {
            is RecentPostAction.OnRefresh -> {
                loadPosts()
            }

            else -> {}
        }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            postRepository
                .getPosts(page = 1, perPage = 30, Strategy.NEW)
                .onSuccess { posts ->
                    _state.update {
                        it.copy(
                            posts = posts.map { post -> post.toCardPostUi() },
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(RecentPostsEvent.Error(error))
                }
        }
    }
}