package com.harukadev.tabnews.posts.presentation.content_post.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.tabnews.R
import com.harukadev.tabnews.posts.presentation.components.DashedVerticalDivider
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun PostContentVote(
    modifier: Modifier = Modifier,
    tabcoins: Int,
    onUpVote: () -> Unit = {},
    onDownVote: () -> Unit = {},
) {
    val contentColor = MaterialTheme.colorScheme.onTertiary

    Column(
        modifier = modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.caret_up),
            contentDescription = stringResource(R.string.hint_upvote_this_post),
            tint = contentColor,
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .size(24.dp)
                .clickable { onUpVote() })

        Text(
            text = tabcoins.toString(),
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.caret_down),
            contentDescription = stringResource(R.string.hint_downvote_this_post),
            tint = contentColor,
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .size(24.dp)
                .clickable { onDownVote() })

        Box(modifier = Modifier.height(16.dp))

        DashedVerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp),
            color = contentColor.copy(alpha = .3f),
            thickness = 2.dp,
            dashLength = 8.dp,
            gapLength = 4.dp
        )
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
private fun PostContentVotePreview() {
    AppTheme {
        PostContentVote(
            tabcoins = 9
        )
    }
}