package com.harukadev.tabnews.posts.presentation.relevant_posts

import com.harukadev.tabnews.posts.presentation.models.CardPostUi

data class RelevantPostsState(
    val posts: List<CardPostUi> = emptyList(),
    val isLoading: Boolean = true,
    val postSelected: CardPostUi? = null
)