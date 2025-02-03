package com.harukadev.tabnews.posts.presentation.recent_posts

import com.harukadev.tabnews.posts.presentation.models.CardPostUi

data class RecentPostsState(
    val posts: List<CardPostUi> = emptyList(),
    val isLoading: Boolean = true,
    val postSelected: CardPostUi? = null
)