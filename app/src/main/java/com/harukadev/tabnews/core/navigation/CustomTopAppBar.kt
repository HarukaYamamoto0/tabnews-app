package com.harukadev.tabnews.core.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.harukadev.tabnews.BuildConfig
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme

@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier) {
    val contentColor = Color.White

    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 16.dp)
    ) {
        val context = LocalContext.current
        val intent by lazy {
            Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TABNEWS_URL))
        }

        val (refIcon, refTabCoinIcon, refTabCoin, refTabCashIcon, refTabCash) = createRefs()

        Image(painter = painterResource(R.drawable.tabnews),
            contentDescription = "Icon",
            modifier = Modifier
                .size(48.dp)
                .constrainAs(refIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable { context.startActivity(intent) }
        )

        Image(
            painter = painterResource(R.drawable.ic_tabcoin),
            contentDescription = stringResource(R.string.tabCoin),
            modifier = Modifier
                .size(15.dp)
                .constrainAs(refTabCoinIcon) {
                    end.linkTo(refTabCoin.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clip(RoundedCornerShape(2.dp))
        )

        Text(
            "102",
            modifier = Modifier
                .constrainAs(refTabCoin) {
                    end.linkTo(refTabCash.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 5.dp, end = 25.9.dp),
            color = contentColor,
            fontSize = 15.sp,
        )

        Image(
            painter = painterResource(R.drawable.ic_tabcash),
            contentDescription = stringResource(R.string.tabCash),
            modifier = Modifier
                .size(15.dp)
                .constrainAs(refTabCashIcon) {
                    start.linkTo(refTabCoin.end)
                    end.linkTo(refTabCash.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clip(RoundedCornerShape(2.dp))
        )

        Text(
            "354",
            modifier = Modifier
                .constrainAs(refTabCash) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 12.dp, end = 10.dp),
            color = contentColor,
            fontSize = 15.sp
        )
    }
}

@PreviewLightDark
@Composable
private fun CustomTopAppBarPreview() {
    AppTheme {
        CustomTopAppBar()
    }
}