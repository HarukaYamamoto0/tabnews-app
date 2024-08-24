package com.harukadev.tabnews.data

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class PostContent(
    val body: String,
    val comments: Int,
    val createdAtRaw: String,
    val deletedAtRaw: String?,
    val ownerUsername: String,
    val publishedAtRaw: String,
    val slug: String,
    val sourceUrl: String?,
    val status: String,
    val tabcoins: Int,
    val tabcoinsCredit: Int,
    val tabcoinsDebit: Int,
    val title: String,
    val type: String,
    val updatedAtRaw: String
) {
    private fun formatDate(dateString: String): String {
        val instant = Instant.parse(dateString)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val javaDateTime = dateTime.toJavaLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return javaDateTime.format(formatter)
    }

    val createdAt: String
        get() = formatDate(createdAtRaw)

    val updatedAt: String
        get() = formatDate(updatedAtRaw)

    val publishedAt: String
        get() = formatDate(publishedAtRaw)

    val deletedAt: String? = if (deletedAtRaw != null) formatDate(deletedAtRaw) else null
}
