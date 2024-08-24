package com.harukadev.tabnews.data

import com.harukadev.tabnews.utils.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentRaw(
    val body: String? = Constants.STRING_NOTHING,
    @SerialName("children_deep_count")
    val childrenDeepCount: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("deleted_at")
    val deletedAt: String?,
    val id: String,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("owner_username")
    val ownerUsername: String,
    @SerialName("parent_id")
    val parentId: String?,
    @SerialName("published_at")
    val publishedAt: String,
    val slug: String,
    @SerialName("source_url")
    val sourceUrl: String?,
    val status: String,
    val tabcoins: Int,
    @SerialName("tabcoins_credit")
    val tabcoinsCredit: Int,
    @SerialName("tabcoins_debit")
    val tabcoinsDebit: Int,
    val title: String,
    val type: String,
    @SerialName("updated_at")
    val updatedAt: String
) {
    fun toFeedContent(): FeedContent {
        return FeedContent(
            comments = childrenDeepCount,
            createdAt = createdAt,
            ownerUsername = ownerUsername,
            publishedAt = publishedAt,
            slug = slug,
            tabcoins = tabcoins,
            title = title,
            updatedAt = updatedAt,
        )
    }

    fun toPostContent(): PostContent {
        return PostContent(
            body = body ?: Constants.STRING_NOTHING,
            comments = childrenDeepCount,
            createdAtRaw = createdAt,
            deletedAtRaw = deletedAt,
            ownerUsername = ownerUsername,
            publishedAtRaw = publishedAt,
            slug = slug,
            sourceUrl = sourceUrl,
            status = status,
            tabcoins = tabcoins,
            tabcoinsCredit = tabcoinsCredit,
            tabcoinsDebit = tabcoinsDebit,
            title = title,
            type = type,
            updatedAtRaw = updatedAt,
        )
    }
}
