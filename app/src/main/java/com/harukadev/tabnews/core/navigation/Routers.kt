package com.harukadev.tabnews.core.navigation

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

sealed interface Router

@Immutable
@Serializable
data object RelevantPostsRouter : Router

@Immutable
@Serializable
data object RecentPostsRouter : Router

@Immutable
@Serializable
data class PostContentRouter(
    val ownerUsername: String,
    val slug: String
) : Router