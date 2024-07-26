package com.harukadev.tabnews.api

import com.harukadev.tabnews.data.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TabNewsApi {
    private val baseUrl = "https://www.tabnews.com.br/api/v1"

    enum class PostStrategy(val strategy: String) {
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

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                serializersModule
            })
        }
    }

    suspend fun getPost(page: Int, perPage: Int, strategy: PostStrategy): List<Post> {
        val response: List<Post> =
            client
                .get("$baseUrl/contents?page=$page&per_page=$perPage&strategy=${strategy.strategy}")
                .body()
        return response
    }

    suspend fun getPostFromUser(owner: String, slug: String): Post {
        val response: Post =
            client
                .get("$baseUrl/contents/$owner/$slug")
                .body()
        return response
    }


    fun close() {
        client.close()
    }
}
