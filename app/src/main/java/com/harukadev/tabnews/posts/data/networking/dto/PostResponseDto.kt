package com.harukadev.tabnews.posts.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponseDto(
    val data: List<PostDto>
)