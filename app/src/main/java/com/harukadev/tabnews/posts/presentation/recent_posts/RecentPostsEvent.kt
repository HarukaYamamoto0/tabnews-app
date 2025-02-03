package com.harukadev.tabnews.posts.presentation.recent_posts

import com.harukadev.tabnews.core.data.networking.NetworkError

sealed interface RecentPostsEvent {
    data class Error(val error: NetworkError) : RecentPostsEvent
}