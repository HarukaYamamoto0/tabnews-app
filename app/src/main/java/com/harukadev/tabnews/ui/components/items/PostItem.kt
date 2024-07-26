package com.harukadev.tabnews.ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.harukadev.tabnews.data.Post
import com.harukadev.tabnews.data.fakeData
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens

@Preview(showBackground = true)
@Composable
fun PostItem(index: Int = 0, post: Post = fakeData[0], onClick: () -> Unit = {}) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.background)
            .padding()
            .padding(bottom = 17.dp)
    ) {
        val (refIndex, refTitle, refInfos) = createRefs()

        Text(
            text = "$index.",
            modifier = Modifier
                .constrainAs(refIndex) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(refTitle.start)
                }
                .defaultMinSize(minWidth = 28.dp),
            style = TextStyle(color = Colors.text, fontWeight = FontWeight.Bold)
        )

        Text(
            text = post.title,
            style = TextStyle(color = Colors.text, lineBreak = LineBreak.Simple),
            modifier = Modifier
                .constrainAs(refTitle) {
                    start.linkTo(refIndex.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(refInfos.top)
                }
                .padding(end = 20.dp)
                .padding(bottom = 5.dp)
                .clickable {
                    onClick()
                },
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontSize = Dimens.fontSizeOfPostCardTitle,
        )

        val infos = """
            ${post.tabcoins} tabcoins
            ${post.totalComments} comments
            ${post.author}
            ${post.createdAt}
        """.trimIndent().replace(Regex("\n"), " • ")

        Text(
            text = infos,
            style = TextStyle(
                color = Colors.onDarkText,
                fontSize = Dimens.fontSizeOfPostCardInformation,
                lineBreak = LineBreak.Simple
            ),
            modifier = Modifier.constrainAs(refInfos) {
                start.linkTo(refIndex.end)
                top.linkTo(refTitle.bottom)
                bottom.linkTo(parent.bottom)
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}