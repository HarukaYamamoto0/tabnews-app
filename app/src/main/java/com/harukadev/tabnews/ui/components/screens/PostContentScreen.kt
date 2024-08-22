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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.harukadev.tabnews.api.TabNewsApi
import com.harukadev.tabnews.data.Post
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens
import kotlinx.coroutines.runBlocking

@Composable
fun PostContentScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    PostContentScreenRaw(modifier)
}

@Composable
fun PostContentScreenRaw(modifier: Modifier = Modifier) {
    val (owner, slug) = arrayOf("HarukaYamamoto0", "crise-do-impostor")
    lateinit var post: Post

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Colors.background)
            .verticalScroll(rememberScrollState())
            .padding(0.dp)
            .padding(Dimens.paddingPostContent)
    ) {
        val (refTabcoinLayout, refAuthor, refCreatedAt, refContent) = createRefs()

        runBlocking {
            val api = TabNewsApi()
            post = api.getPostFromUser(owner, slug)
            api.close()
        }

        Column(
            modifier = Modifier.constrainAs(refTabcoinLayout) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, null, tint = Colors.onDarkIcon)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(
                text = post.tabcoins.toString(), style = TextStyle(
                    color = Colors.onText,
                    fontSize = Dimens.fontSizeOfPostContentTabcoin,
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
                    start.linkTo(refTabcoinLayout.end)
                }
                .padding(start = 15.dp, end = 7.dp)
                .offset(y = (-5).dp)
        ) {
            Text(
                text = post.author,
                style = TextStyle(
                    color = Colors.onText,
                    fontSize = Dimens.fontSizeOfPostContentCreatedAt,
                ), modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
        }

        Text(
            text = post.createdAt,
            style = TextStyle(
                color = Colors.onDarkText,
                fontSize = Dimens.fontSizeOfPostContentCreatedAt
            ),
            modifier = Modifier.constrainAs(refCreatedAt) {
                top.linkTo(parent.top)
                start.linkTo(refAuthor.end)
            }
        )

        post.body?.let {
            Text(text = it, modifier = Modifier
                .constrainAs(refContent) {
                    top.linkTo(refAuthor.bottom)
                    start.linkTo(refTabcoinLayout.end)
                }
                //.padding(start = 15.dp, top = 10.dp)
                .padding(end = 10.dp))
        }
    }
}

open class PostContentScreen {

}
