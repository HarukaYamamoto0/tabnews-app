package com.harukadev.tabnews.posts.domain.mappers

import com.harukadev.tabnews.posts.data.networking.dto.CardPostDto
import com.harukadev.tabnews.posts.data.networking.dto.PostContentDto
import com.harukadev.tabnews.posts.domain.CardPost
import com.harukadev.tabnews.posts.domain.PostContent

fun CardPostDto.toPost(): CardPost {
    return CardPost(
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

fun PostContentDto.toPostContent(): PostContent {
    return PostContent(
        type = type,
        id = id,
        ownerId = ownerId,
        parentId = parentId,
        ownerUsername = ownerUsername,
        slug = slug,
        title = title,
        body = body,
        comments = comments,
        sourceUrl = sourceUrl,
        status = status,
        tabcoins = tabcoins,
        tabcoinsCredit = tabcoinsCredit,
        tabcoinsDebit = tabcoinsDebit,
        createdAt = createdAt,
        deletedAt = deletedAt,
        publishedAt = publishedAt,
        updatedAt = updatedAt
    )
}
