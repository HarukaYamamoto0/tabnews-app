package com.harukadev.tabnews.posts.presentation.content_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harukadev.tabnews.core.domain.onError
import com.harukadev.tabnews.core.domain.onSuccess
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.domain.mappers.toPostContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostContentViewModel(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PostContentState())
    val state: StateFlow<PostContentState> = _state

    fun loadPost(ownerUsername: String, slug: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            postRepository
                .getPost(
                    slug = slug,
                    ownerUsername = ownerUsername
                )
                .onSuccess { post ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            post = post.toPostContent()
                        )
                    }
                }
                .onError {
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}