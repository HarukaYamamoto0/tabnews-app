package com.harukadev.tabnews.data

data class FeedContent(
    val comments: Int,
    val createdAt: String,
    val ownerUsername: String,
    val publishedAt: String,
    val slug: String,
    val tabcoins: Int,
    val title: String,
    val updatedAt: String
)