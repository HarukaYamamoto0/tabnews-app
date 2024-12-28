package com.harukadev.tabnews.posts.presentation.relevant_posts

import com.harukadev.tabnews.posts.presentation.models.CardPostUi

sealed interface RelevantPostAction {
    data class OnPostClick(val post: CardPostUi) : RelevantPostAction
    data class OnRefresh(val page: Int) : RelevantPostAction
}