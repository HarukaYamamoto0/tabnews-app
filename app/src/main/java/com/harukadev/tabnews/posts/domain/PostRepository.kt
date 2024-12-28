package com.harukadev.tabnews.posts.domain

import com.harukadev.tabnews.core.data.networking.NetworkError
import com.harukadev.tabnews.core.domain.Result

object Strategy {
    const val NEW = "new"
    const val OLD = "old"
    const val RELEVANT = "relevant"
}

interface PostRepository {
    suspend fun getPost(
        username: String,
        slug: String
    ): Result<Post, NetworkError>

    suspend fun getPosts(
        page: Int,
        perPage: Int,
        strategy: String
    ): Result<List<Post>, NetworkError>
}