package com.harukadev.tabnews.di

import com.harukadev.tabnews.core.data.networking.HttpClientFactory
import com.harukadev.tabnews.posts.data.networking.RemotePostRepository
import com.harukadev.tabnews.posts.domain.PostRepository
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemotePostRepository) { bind<PostRepository>() }
    viewModelOf(::RelevantPostsViewModel)
}