package com.harukadev.marktocompose.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.fleeksoft.ksoup.nodes.Element
import com.harukadev.marktocompose.R

@Composable
fun MarkImage(modifier: Modifier = Modifier, element: Element) {
    MarkImageRaw(modifier, element)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MarkImageRaw(
    modifier: Modifier = Modifier,
    element: Element
) {
    val src = element.absUrl("src")
    val alt = element.absUrl("alt")
    GlideImage(
        model = src,
        contentDescription = alt,
        alignment = Alignment.Center,
        modifier = Modifier.then(modifier),
        loading = placeholder(R.drawable.loading_image),
        failure =placeholder(R.drawable.error_loading_image),
    )
}