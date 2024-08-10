package com.harukadev.marktocompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.nodes.Node
import com.fleeksoft.ksoup.nodes.TextNode
import com.harukadev.marktocompose.components.MarkHeading
import com.harukadev.marktocompose.components.MarkImage
import com.harukadev.marktocompose.components.MarkLink
import com.harukadev.marktocompose.components.MarkText
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

@Composable
fun MarkdownToComposable(modifier: Modifier = Modifier, markdown: String) {
    val flavour = CommonMarkFlavourDescriptor()
    val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)

    val html = HtmlGenerator(markdown, parsedTree, flavour)
        .generateHtml()
        .replace("<p>", "")
        .replace("</p>", "")
    val document = Ksoup.parse(html = html)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier)
    ) {
        document.body().childNodes().forEach { node ->
            RenderNode(node)
        }
    }
}

@Composable
fun RenderNode(node: Node) {
    when (node) {
        is TextNode -> {
            MarkText(element = Element(node.text()))
        }

        is Element -> {
            when (node.tagName()) {
                "p" -> MarkText(element = node)
                "img" -> MarkImage(element = node)
                "h1" -> MarkHeading(
                    element = node,
                    level = 1
                )

                "h2" -> MarkHeading(
                    element = node,
                    level = 2
                )

                "h3" -> MarkHeading(
                    element = node,
                    level = 3
                )

                "h4" -> MarkHeading(
                    element = node,
                    level = 4
                )

                "h5" -> MarkHeading(
                    element = node,
                    level = 5
                )

                "h6" -> MarkHeading(
                    element = node,
                    level = 6
                )

                "a" -> MarkLink(element = node)
                else -> MarkText(element = node)
            }
        }
    }
}
