package com.harukadev.tabnews.core.presentation

import android.content.Context
import com.harukadev.tabnews.R
import com.harukadev.tabnews.core.data.networking.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TO_MANY_REQUEST -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown

    }

    return context.getString(resId)
}
