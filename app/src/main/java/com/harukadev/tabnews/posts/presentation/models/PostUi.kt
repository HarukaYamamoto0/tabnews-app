package com.harukadev.tabnews.posts.presentation.models

data class PostUi(
    val slug: String,
    val title: String,
    val ownerUsername: String,
    val body: String,
    val readingTime: String,
    val createdAt: String
)

//fun Post.toPostUi() : PostUi {
//    return PostUi(
//        slug = slug,
//        title = title,
//        ownerUsername = ownerUsername,
//        body = body,
//        readingTime = readingTime,
//    )
//}
