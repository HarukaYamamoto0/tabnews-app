package com.harukadev.tabnews.posts.presentation.content_post

import com.harukadev.tabnews.posts.domain.PostContent

data class PostContentState(
    val isLoading: Boolean = true,
    val post: PostContent? = null
)
