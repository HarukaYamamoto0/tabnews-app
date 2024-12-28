package com.harukadev.tabnews.posts.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DashedVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    dashLength: Dp = 8.dp,
    gapLength: Dp = 4.dp
) {
    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
    ) {
        val canvasHeight = size.height
        val dashPx = dashLength.toPx()
        val gapPx = gapLength.toPx()
        val totalLength = dashPx + gapPx

        var currentY = 0f
        while (currentY < canvasHeight) {
            val endY = (currentY + dashPx).coerceAtMost(canvasHeight)
            drawLine(
                color = color,
                start = Offset(x = size.width / 2, y = currentY),
                end = Offset(x = size.width / 2, y = endY),
                strokeWidth = thickness.toPx()
            )
            currentY += totalLength
        }
    }
}