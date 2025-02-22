package com.harukadev.tabnews.posts.presentation.content_post

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.harukadev.tabnews.R
import com.harukadev.tabnews.posts.presentation.components.MessageScreen
import com.harukadev.tabnews.posts.presentation.content_post.components.PostContentHeader
import com.harukadev.tabnews.posts.presentation.content_post.components.PostContentVote
import com.harukadev.tabnews.posts.presentation.models.toDisplayableDate
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun PostContentScreen(
    modifier: Modifier = Modifier, state: PostContentState
) {
    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (state.post !== null) {
            ConstraintLayout(
                modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp)
            ) {
                val (voteLayoutRef, headerLayoutRef, contentLayoutRef) = createRefs()

                PostContentVote(
                    modifier = Modifier
                        .fillMaxHeight()
                        .constrainAs(voteLayoutRef) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(contentLayoutRef.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .padding(top = 16.dp, end = 7.dp, bottom = 16.dp),
                    tabcoins = state.post.tabcoins,
                )

                PostContentHeader(modifier = Modifier
                    .constrainAs(headerLayoutRef) {
                        start.linkTo(voteLayoutRef.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 16.dp, end = 7.dp),
                    username = state.post.ownerUsername,
                    readTime = "5 min read",
                    createdAt = state.post.createdAt.toDisplayableDate().formatted)

                Column(modifier = Modifier
                    .constrainAs(contentLayoutRef) {
                        start.linkTo(voteLayoutRef.end)
                        top.linkTo(headerLayoutRef.bottom)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(end = 7.dp)) {
                    Text(
                        text = state.post.title,
                        fontSize = 30.sp,
                        lineHeight = 45.sp,
                        fontWeight = FontWeight.Bold,
                        color = contentColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = state.post.body.trimIndent(),
                        color = contentColor,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        } else {
            MessageScreen(
                icon = R.drawable.forest,
                title = R.string.content_not_found,
                message = R.string.post_not_found
            )
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun ReadPostScreenPreview() {
    AppTheme {
        PostContentScreen(
            state = PostContentState(
                isLoading = false, post = null
            )
        )
    }
}