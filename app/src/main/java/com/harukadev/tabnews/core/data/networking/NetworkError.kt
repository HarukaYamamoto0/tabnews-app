package com.harukadev.tabnews.core.data.networking

import com.harukadev.tabnews.core.domain.Error

enum class NetworkError : Error{
    REQUEST_TIMEOUT,
    TO_MANY_REQUEST,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}