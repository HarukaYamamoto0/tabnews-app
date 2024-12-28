package com.harukadev.tabnews.posts.domain

import com.harukadev.tabnews.core.data.networking.NetworkError
import com.harukadev.tabnews.core.domain.Result
import com.harukadev.tabnews.posts.data.networking.dto.PostContentDto

object Strategy {
    const val NEW = "new"
    const val OLD = "old"
    const val RELEVANT = "relevant"
}

interface PostRepository {
    suspend fun getPost(
        ownerUsername: String,
        slug: String
    ): Result<PostContentDto, NetworkError>

    suspend fun getPosts(
        page: Int,
        perPage: Int,
        strategy: String
    ): Result<List<CardPost>, NetworkError>
}