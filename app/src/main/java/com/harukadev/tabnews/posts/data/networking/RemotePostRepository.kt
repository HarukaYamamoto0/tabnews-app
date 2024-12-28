package com.harukadev.tabnews.posts.data.networking

import android.util.Log
import com.harukadev.tabnews.BuildConfig
import com.harukadev.tabnews.core.data.networking.NetworkError
import com.harukadev.tabnews.core.data.networking.constructUrl
import com.harukadev.tabnews.core.data.networking.safeCall
import com.harukadev.tabnews.core.domain.Result
import com.harukadev.tabnews.core.domain.map
import com.harukadev.tabnews.posts.data.networking.dto.PostDto
import com.harukadev.tabnews.posts.data.networking.dto.PostResponseDto
import com.harukadev.tabnews.posts.domain.Post
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.domain.mappers.toPost
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePostRepository(
    private val httpClient: HttpClient
) : PostRepository {
    override suspend fun getPost(username: String, slug: String): Result<Post, NetworkError> {
        return safeCall<Post> {
            httpClient.get(constructUrl("/contents/${username}/${slug}"))
        }
    }

    override suspend fun getPosts(
        page: Int,
        perPage: Int,
        strategy: String
    ): Result<List<Post>, NetworkError> {
        return safeCall<List<PostDto>> {
            httpClient.get(constructUrl("/contents?page=$page&per_page=$perPage&strategy=$strategy"))
        }.map { response ->
            response.map { it.toPost() }
        }
    }
}