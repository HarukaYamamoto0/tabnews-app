package com.harukadev.tabnews.api

import androidx.compose.foundation.lazy.itemsIndexed
import com.harukadev.tabnews.data.ContentRaw
import com.harukadev.tabnews.data.PostContent
import com.harukadev.tabnews.ui.components.components.items
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
        val response: List<ContentRaw> =
            client
                .get("$baseUrl/contents?page=$page&per_page=$perPage&strategy=${strategy.strategy}")
                .body()

        val postContentList: List<PostContent> = response.map { contentRaw ->
            contentRaw.toPostContent()
        }

        return postContentList
    }


    suspend fun getPostFromUser(owner: String, slug: String): PostContent {
        val response: ContentRaw = client.get("$baseUrl/contents/$owner/$slug").body()
        return response.toPostContent()
    }


    fun close() {
        client.close()
    }
}
