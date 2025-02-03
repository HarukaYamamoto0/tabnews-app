package com.harukadev.tabnews.posts.presentation.recent_posts

import com.harukadev.tabnews.posts.presentation.models.CardPostUi

sealed interface RecentPostAction {
    data class OnPostClick(val post: CardPostUi) : RecentPostAction
    data class OnRefresh(val page: Int) : RecentPostAction
}