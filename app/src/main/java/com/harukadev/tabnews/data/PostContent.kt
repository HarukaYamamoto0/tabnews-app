package com.harukadev.tabnews.data

import kotlinx.serialization.Serializable

@Serializable
data class PostContent(
    val body: String,
    val comments: Int,
    val createdAt: String,
    val deletedAt: String?,
    val ownerUsername: String,
    val publishedAt: String,
    val slug: String,
    val sourceUrl: String?,
    val status: String,
    val tabcoins: Int,
    val tabcoinsCredit: Int,
    val tabcoinsDebit: Int,
    val title: String,
    val type: String,
    val updatedAt: String
)