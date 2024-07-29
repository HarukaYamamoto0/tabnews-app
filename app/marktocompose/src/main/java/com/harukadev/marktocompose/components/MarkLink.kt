package com.harukadev.marktocompose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ripple
import com.fleeksoft.ksoup.nodes.Element
import com.harukadev.marktocompose.ui.Dimens

@Composable
fun MarkLink(
    modifier: Modifier = Modifier,
    element: Element,
    style: TextStyle = TextStyle(),
) {
    MarkLinkRaw(modifier, element, style)
}

@Composable
private fun MarkLinkRaw(
    modifier: Modifier = Modifier,
    element: Element,
    style: TextStyle = TextStyle(),
) {
    val uriHandler = LocalUriHandler.current
    val url = element.absUrl("href")
    val interactionSource = remember { MutableInteractionSource() }

    Text(
        text = AnnotatedString(element.text()),
        style = TextStyle(
            color = Color.Black,
            textDecoration = TextDecoration.Underline,
            fontSize = Dimens.fontSizeBody
        ).merge(style),
        modifier = Modifier
            .fillMaxWidth()
            .pointerHoverIcon(PointerIcon.Hand)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            .clickable(
                interactionSource = interactionSource,
                onClick = { uriHandler.openUri(url) },
                indication = ripple()
            )
            .then(modifier),
    )
}