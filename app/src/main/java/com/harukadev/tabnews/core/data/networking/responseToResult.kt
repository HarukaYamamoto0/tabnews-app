package com.harukadev.tabnews.core.data.networking

import com.harukadev.tabnews.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success<T>(response.body())
            } catch (e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }

        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TO_MANY_REQUEST)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

        else -> Result.Error(NetworkError.UNKNOWN)
    }

}