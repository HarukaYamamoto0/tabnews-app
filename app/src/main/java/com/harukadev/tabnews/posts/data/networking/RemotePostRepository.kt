package com.harukadev.tabnews.posts.data.networking

import com.harukadev.tabnews.core.data.networking.NetworkError
import com.harukadev.tabnews.core.data.networking.constructUrl
import com.harukadev.tabnews.core.data.networking.safeCall
import com.harukadev.tabnews.core.domain.Result
import com.harukadev.tabnews.core.domain.map
import com.harukadev.tabnews.posts.data.networking.dto.CardPostDto
import com.harukadev.tabnews.posts.data.networking.dto.PostContentDto
import com.harukadev.tabnews.posts.domain.CardPost
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.domain.mappers.toPost
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemotePostRepository(
    private val httpClient: HttpClient
) : PostRepository {
    override suspend fun getPost(
        ownerUsername: String,
        slug: String
    ): Result<PostContentDto, NetworkError> {
        return safeCall<PostContentDto> {
            httpClient.get(constructUrl("/contents/${ownerUsername}/${slug}"))
        }
    }

    override suspend fun getPosts(
        page: Int,
        perPage: Int,
        strategy: String
    ): Result<List<CardPost>, NetworkError> {
        return safeCall<List<CardPostDto>> {
            httpClient.get(constructUrl("/contents?page=$page&per_page=$perPage&strategy=$strategy"))
        }.map { response ->
            response.map { it.toPost() }
        }
    }
}