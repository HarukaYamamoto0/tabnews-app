package com.harukadev.tabnews.ui.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.harukadev.tabnews.api.TabNewsApi
import com.harukadev.tabnews.data.PostContent
import com.harukadev.tabnews.ui.components.components.PostContentRoute
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens
import com.harukadev.tabnews.utils.fakedata.fakePosts
import kotlinx.coroutines.runBlocking

@Composable
private fun PostContentScreenRaw(modifier: Modifier = Modifier, args: PostContentRoute) {
    val (ownerUsername, slug) = args
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Colors.background)
            .verticalScroll(rememberScrollState())
            .padding(0.dp)
            .padding(Dimens.paddingPostContent)
    ) {
        val (refTabCoinLayout, refAuthor, refCreatedAt, refContent) = createRefs()
        lateinit var postContent: PostContent

        runBlocking {
            val api = TabNewsApi()
            postContent = api.getPostFromUser(ownerUsername, slug)
            api.close()
        }

        Column(
            modifier = Modifier.constrainAs(refTabCoinLayout) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, null, tint = Colors.onDarkIcon)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(
                text = postContent.tabcoins.toString(), style = TextStyle(
                    color = Colors.onText,
                    fontSize = Dimens.fontSizeOfPostContentTabCoin,
                )
            )
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Icon(Icons.Filled.KeyboardArrowDown, null, tint = Colors.onDarkIcon)
        }

        Surface(
            color = Colors.primarySurface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .constrainAs(refAuthor) {
                    top.linkTo(parent.top)
                    start.linkTo(refTabCoinLayout.end)
                }
                .padding(start = 15.dp, end = 7.dp)
                .offset(y = (-5).dp)
        ) {
            Text(
                text = postContent.ownerUsername,
                style = TextStyle(
                    color = Colors.onText,
                    fontSize = Dimens.fontSizeOfPostContentCreatedAt,
                ), modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
        }

        Text(
            text = postContent.createdAt,
            style = TextStyle(
                color = Colors.onDarkText,
                fontSize = Dimens.fontSizeOfPostContentCreatedAt
            ),
            modifier = Modifier.constrainAs(refCreatedAt) {
                top.linkTo(parent.top)
                start.linkTo(refAuthor.end)
            }
        )

        Text(
            text = postContent.body,
            modifier = Modifier
                .constrainAs(refContent) {
                    top.linkTo(refAuthor.bottom)
                    start.linkTo(refTabCoinLayout.end)
                }
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .padding(end = 10.dp),
            style = TextStyle(color = Colors.text)

        )

    }
}

@Composable
fun PostContentScreen(args: PostContentRoute, modifier: Modifier = Modifier) {
    PostContentScreenRaw(modifier, args)
}

@Preview
@Composable
private fun PostContentScreenPreview() {
    PostContentScreenRaw(
        args = PostContentRoute(
            ownerUsername = fakePosts[0].ownerUsername,
            slug = fakePosts[0].slug
        )
    )
}