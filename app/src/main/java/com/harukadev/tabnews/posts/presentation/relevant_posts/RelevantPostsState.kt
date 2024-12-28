package com.harukadev.tabnews.posts.presentation.relevant_posts

import com.harukadev.tabnews.posts.presentation.models.PostUi

data class RelevantPostsState(
    val posts: List<PostUi> = emptyList(),
    val isLoading: Boolean = true,
    val postSelected: PostUi? = null
)