package com.harukadev.tabnews.data

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class Post(
    val id: String,
    val slug: String,
    val title: String,
    val body: String? = null,
    @SerialName("owner_id")
    val ownerId: String,
    @SerialName("parent_id")
    val parentId: String?,
    val status: String,
    @SerialName("source_url")
    val sourceUrl: String?,
    @SerialName("created_at")
    var createdAtRaw: String,
    @SerialName("updated_at")
    val updatedAtRaw: String,
    @SerialName("published_at")
    val publishedAtRaw: String,
    @SerialName("deleted_at")
    val deletedAtRaw: String?,
    val tabcoins: Int,
    @SerialName("tabcoins_credit")
    val tabcoinsCredit: Int,
    @SerialName("tabcoins_debit")
    val tabcoinsDebit: Int,
    @SerialName("owner_username")
    val author: String,
    @SerialName("children_deep_count")
    val totalComments: Int,
    val type: String
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

