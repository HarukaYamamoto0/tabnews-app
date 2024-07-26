package com.harukadev.tabnews.ui.components.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavBackStackEntry
import com.harukadev.tabnews.data.Post
import com.harukadev.tabnews.data.fakeData
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens
import kotlinx.serialization.json.Json

@Composable
fun PostContentFragment(backStackEntry: NavBackStackEntry, modifier: Modifier = Modifier) {
    val postJson =
        (backStackEntry.arguments?.getString("postJson") ?: fakeData[0]).toString()
    val postData = remember { Json.decodeFromString<Post>(postJson) }
    PostContentRaw(postData)
}

@Preview
@Composable
fun PostContentPreview(modifier: Modifier = Modifier) {
    PostContentRaw(fakeData[0])
}

@Composable
fun PostContentRaw(postData: Post, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Colors.background)
            .padding(0.dp)
            .padding(Dimens.paddingPostContent)
    ) {
        val (refTabcoinLayout, refAuthor, refCreatedAt) = createRefs()

        Column(
            modifier = Modifier.constrainAs(refTabcoinLayout) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.KeyboardArrowUp, null, tint = Colors.onDarkIcon)
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = postData.tabcoins.toString(), style = TextStyle(
                color = Colors.onText,
                fontSize = Dimens.fontSizeOfPostContentTabcoin,
            ))
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
                .padding(start = 15.dp, end = 7.dp).offset(y = (-5).dp)
        ) {
            Text(
                text = postData.author,
                style = TextStyle(
                    color = Colors.onText,
                    fontSize = Dimens.fontSizeOfPostContentCreatedAt,
                ), modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
            )
        }

        Text(
            text = postData.createdAt,
            style = TextStyle(
                color = Colors.onDarkText,
                fontSize = Dimens.fontSizeOfPostContentCreatedAt
            ),
            modifier = Modifier.constrainAs(refCreatedAt) {
                top.linkTo(parent.top)
                start.linkTo(refAuthor.end)
            }
        )
    }
}