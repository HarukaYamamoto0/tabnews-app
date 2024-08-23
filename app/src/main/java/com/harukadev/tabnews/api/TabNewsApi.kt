package com.harukadev.tabnews.api

import com.harukadev.tabnews.data.PostContent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TabNewsApi {
    private val baseUrl = "https://www.tabnews.com.br/api/v1"

    enum class ContentStrategy(val strategy: String) {
        NEW("new"),
        OLD("old"),
        RELEVANT("relevant");

        override fun toString(): String {
            return strategy
        }
    }

    private val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(HttpCache)

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule
            })
        }
    }

    suspend fun getPost(page: Int, perPage: Int, strategy: ContentStrategy): List<PostContent> {
        val response: List<PostContent> =
            client
                .get("$baseUrl/contents?page=$page&per_page=$perPage&strategy=${strategy.strategy}")
                .body()
        return response
    }

    suspend fun getPostFromUser(owner: String, slug: String): PostContent {
        val response: PostContent = client.get("$baseUrl/contents/$owner/$slug").body()
        return response
    }


    fun close() {
        client.close()
    }
}
