package com.harukadev.tabnews.posts.presentation.models

import com.harukadev.tabnews.posts.domain.Post
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.Locale

data class PostUi(
    val ownerUsername: String,
    val title: String,
    val comments: Int,
    val createdAt: DisplayableDate,
    val tabcoins: Int
)

data class DisplayableDate(
    val value: String,
    val formatted: String
)

fun Post.toPostUi() = PostUi(
    ownerUsername = ownerUsername,
    title = title,
    comments = comments,
    createdAt = createdAt.toDisplayableDate(),
    tabcoins = tabcoins,
)

fun String.toDisplayableDate(): DisplayableDate {
    return try {
        val instant = Instant.parse(this)
        val zonedDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        val relativeTime = getRelativeTime(zonedDateTime)
        val formattedDate = formatDateAutomatically(zonedDateTime)

        DisplayableDate(
            value = this,
            formatted = relativeTime ?: formattedDate
        )
    } catch (e: Exception) {
        DisplayableDate(
            value = this,
            formatted = "invalid date"
        )
    }
}

fun formatDateAutomatically(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return date.format(formatter)
}

fun getRelativeTime(date: LocalDateTime): String? {
    val now = LocalDateTime.now()
    val minutes = ChronoUnit.MINUTES.between(date, now)
    val hours = ChronoUnit.HOURS.between(date, now)
    val days = ChronoUnit.DAYS.between(date, now)

    return when {
        minutes < 1 -> "agora mesmo"
        minutes < 60 -> "$minutes ${if (minutes == 1L) "minuto" else "minutos"} atrás"
        hours < 24 -> "$hours ${if (hours == 1L) "hora" else "horas"} atrás"
        days < 7 -> "$days ${if (days == 1L) "dia" else "dias"} atrás"
        else -> null
    }
}