package com.harukadev.tabnews.posts.presentation.relevant_posts

import com.harukadev.tabnews.core.data.networking.NetworkError

sealed interface RelevantPostsEvent {
    data class Error(val error: NetworkError) : RelevantPostsEvent
}