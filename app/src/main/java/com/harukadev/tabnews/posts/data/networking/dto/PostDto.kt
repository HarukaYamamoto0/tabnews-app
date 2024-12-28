package com.harukadev.tabnews.posts.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("children_deep_count")
    val comments: Int,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,
    val id: String,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("owner_username")
    val ownerUsername: String,
    @SerialName("parent_id")
    val parentId: String? = null,
    @SerialName("published_at")
    val publishedAt: String,
    val slug: String,
    @SerialName("source_url")
    val sourceUrl: String? = null,
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
)