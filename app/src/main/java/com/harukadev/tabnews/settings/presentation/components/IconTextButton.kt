package com.harukadev.tabnews.settings.presentation.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    icon: Int? = null,
    text: String,
    onClick: () -> Unit = {},
    background: Color = MaterialTheme.colorScheme.onBackground,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    iconTint: Color = LocalContentColor.current,
    iconSize: Dp = 24.dp,
    contentDescription: String? = null,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(background)
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable(
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = contentDescription,
                tint = iconTint,
                modifier = Modifier.size(iconSize)
            )
        }
        Text(
            text = text,
            style = textStyle
        )
    }
}

@Preview
@Composable
private fun IconTextButtonPreview() {
    AppTheme {
        IconTextButton(
            icon = R.drawable.bell_fill,
            text = "Button Text",
            onClick = {},
            contentDescription = "Accessible description"
        )
    }
}