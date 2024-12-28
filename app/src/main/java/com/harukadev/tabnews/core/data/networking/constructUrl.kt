package com.harukadev.tabnews.core.data.networking

import com.harukadev.tabnews.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.startsWith(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url
        else -> BuildConfig.BASE_URL + url
    }
}