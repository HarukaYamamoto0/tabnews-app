package com.harukadev.marktocompose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fleeksoft.ksoup.nodes.Element
import com.harukadev.marktocompose.ui.Dimens

@Composable
fun MarkHeading(modifier: Modifier = Modifier, element: Element, level: Int) {
    MarkHeadingRaw(modifier, element, level)
}

@Preview(showBackground = true)
@Composable
private fun MarkHeadingPreview() {
    MarkHeadingRaw(element = Element("title", namespace = "Title"))
}

@Composable
private fun MarkHeadingRaw(modifier: Modifier = Modifier, element: Element, level: Int = 1) {
    val text = element.text()

    when (level) {
        1 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel1,
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = Color.Black, background = Color.Transparent)
        )

        2 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel2,
            fontWeight = FontWeight.Bold
        )

        3 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel3,
            fontWeight = FontWeight.Bold
        )

        4 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel4,
            fontWeight = FontWeight.Bold
        )

        5 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp)
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel5,
            fontWeight = FontWeight.Bold
        )

        6 -> Text(
            text,
            Modifier
                .fillMaxWidth()
                .then(modifier),
            fontSize = Dimens.fontSizeTitleLevel6,
            fontWeight = FontWeight.Bold
        )
    }
}