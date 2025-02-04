package com.harukadev.tabnews.posts.domain

import com.harukadev.tabnews.core.data.networking.NetworkError
import com.harukadev.tabnews.core.domain.Result
import com.harukadev.tabnews.posts.data.networking.dto.PostContentDto

enum class Strategy(val value: String) {
    NEW("new"),
    OLD("old"),
    RELEVANT("relevant")
}

interface PostRepository {
    suspend fun getPost(
        ownerUsername: String,
        slug: String
    ): Result<PostContentDto, NetworkError>

    suspend fun getPosts(
        page: Int,
        perPage: Int,
        strategy: Strategy
    ): Result<List<CardPost>, NetworkError>
}