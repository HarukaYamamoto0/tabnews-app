package com.harukadev.marktocompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.fleeksoft.ksoup.nodes.Element
import com.harukadev.marktocompose.ui.Dimens

@Composable
fun MarkText(modifier: Modifier = Modifier, element: Element, style: TextStyle = TextStyle()) {
    MarkTextRaw(modifier, element, style)
}

@Composable
private fun MarkTextRaw(modifier: Modifier = Modifier, element: Element, style: TextStyle) {
    Text(
        text = element.text(),
        style = TextStyle(fontSize = Dimens.fontSizeBody).merge(style),
        modifier = Modifier.fillMaxWidth().then(modifier)
    )
}