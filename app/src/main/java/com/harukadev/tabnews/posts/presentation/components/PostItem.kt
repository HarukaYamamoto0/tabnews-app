package com.harukadev.tabnews.posts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.harukadev.tabnews.posts.presentation.models.PostUi
import com.harukadev.tabnews.posts.presentation.models.toDisplayableDate
import com.harukadev.tabnews.ui.theme.AppTheme
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostItem(
    position: Int,
    post: PostUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val contentColor = if (isSystemInDarkTheme())
        Color.White
    else Color.Black

    val defaultTextStyle = LocalTextStyle.current.copy(
        fontSize = 16.sp,
        lineBreak = LineBreak.Simple,
        color = contentColor
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
            .padding(bottom = 12.dp, top = 5.dp)
    ) {
        val (refTextPosition, refTextTitle, refRowInfos) = createRefs()

        Text(
            text = "$position.",
            modifier = Modifier
                .constrainAs(refTextPosition) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(refTextTitle.start)
                }
                .defaultMinSize(minWidth = 28.dp),
            style = defaultTextStyle
        )

        Text(
            text = post.title,
            modifier = Modifier
                .constrainAs(refTextTitle) {
                    start.linkTo(refTextPosition.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(refRowInfos.top)
                }
                .padding(end = 30.dp)
                .padding(bottom = 5.dp),
            style = defaultTextStyle,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )

        val defaultTextStyleOfInfos = LocalTextStyle.current.copy(
            lineBreak = LineBreak.Simple,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        val divider = " • "

        FlowRow(
            modifier = Modifier.constrainAs(refRowInfos) {
                start.linkTo(refTextPosition.end)
                top.linkTo(refTextTitle.bottom)
                bottom.linkTo(parent.bottom)
            }
        ) {
            PostItemInfoText(
                text = "${post.tabcoins} tabcoins$divider",
            )
            PostItemInfoText(
                text = "${post.comments} comments$divider",
            )
            PostItemInfoText(
                text = post.ownerUsername + divider,
            )
            PostItemInfoText(
                text = post.createdAt.formatted,
            )
        }
    }
}
//
//
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.background)
//            .clip(RoundedCornerShape(8.dp))
//            .clickable { onClick() }
//            .padding(bottom = 7.dp, top = 5.dp)
//    ) {
//        Text(
//            text = "$position.",
//            modifier = modifier.defaultMinSize(minWidth = 28.dp),
//            style = defaultTextStyle
//        )
//
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            Text(
//                text = post.title,
//                style = defaultTextStyle,
//                modifier = Modifier.padding(end = 10.dp),
//                maxLines = 3,
//                overflow = TextOverflow.Ellipsis,
//            )
//
//            val defaultTextStyleOfInfos = defaultTextStyle.copy(
//                fontWeight = FontWeight.Normal,
//                fontSize = 12.sp,
//                color = MaterialTheme.colorScheme.onBackground
//            )
//            val divider = " • "
//
//            FlowRow(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "${post.tabcoins} tabcoins",
//                    style = defaultTextStyleOfInfos
//                )
//                Text(
//                    text = "$divider${post.comments} comments",
//                    style = defaultTextStyleOfInfos
//                )
//                Text(
//                    text = divider + post.ownerUsername,
//                    style = defaultTextStyleOfInfos,
//                    maxLines = 2,
//
//                    overflow = TextOverflow.Ellipsis
//                )
//                Text(
//                    text = divider + post.createdAt.formatted,
//                    style = defaultTextStyleOfInfos,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//        }


@PreviewLightDark
@Composable
private fun PostItemPreview() {
    AppTheme {
        PostItem(1, previewPostUi)
    }
}

internal val previewPostUi = PostUi(
    ownerUsername = "harukadev" + Random.nextInt(200000).toString(),
    title = "Pesquisadores desenvolvem técnica que escrever " +
            "habilidades no cérebro humano ao estilo Matrix",
    comments = 16,
    tabcoins = 35,
    createdAt = "2024-12-05T03:14:14.143Z".toDisplayableDate()
)