package com.harukadev.tabnews.posts.domain

data class Post(
    val id: String,
    val ownerUsername: String,
    val slug: String,
    val title: String,
    val comments: Int,
    val createdAt: String,
    val publishedAt: String,
    val sourceUrl: String? = null,
    val tabcoins: Int,
    val tabcoinsCredit: Int,
    val tabcoinsDebit: Int,
    val updatedAt: String
)