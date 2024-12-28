package com.harukadev.tabnews.posts.domain.mappers

import com.harukadev.tabnews.posts.data.networking.dto.PostDto
import com.harukadev.tabnews.posts.domain.Post

fun PostDto.toPost(): Post {
    return Post(
        id = id,
        ownerUsername = ownerUsername,
        slug = slug,
        title = title,
        comments = comments,
        createdAt = createdAt,
        publishedAt = publishedAt,
        sourceUrl = sourceUrl,
        tabcoins = tabcoins,
        tabcoinsCredit = tabcoinsCredit,
        tabcoinsDebit = tabcoinsDebit,
        updatedAt = updatedAt,
    )
}
