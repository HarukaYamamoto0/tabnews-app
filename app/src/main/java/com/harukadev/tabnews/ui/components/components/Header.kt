package com.harukadev.tabnews.ui.components.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens
import com.harukadev.tabnews.utils.composables.bounceClick

@Preview
@Composable
fun Header() {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp)
            .background(Colors.onBackground)
            .padding(horizontal = 16.dp)
    ) {
        val context = LocalContext.current
        val intent by lazy {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tabnews.com.br/"))
        }

        val (refIcon, refTabcoinIcon, refTabcoin, refTabCashIcon, refTabCash) = createRefs()

        Image(painter = painterResource(R.drawable.tabnews),
            contentDescription = "Icon",
            modifier = Modifier
                .size(48.dp)
                .constrainAs(refIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .bounceClick { context.startActivity(intent) }
        )

        Image(painter = painterResource(R.drawable.ic_tabcoin),
            contentDescription = stringResource(R.string.tabcoin),
            modifier = Modifier
                .size(10.dp)
                .constrainAs(refTabcoinIcon) {
                    end.linkTo(refTabcoin.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }.clip(RoundedCornerShape(2.dp))
        )

        Text(
            "102",
            modifier = Modifier
                .constrainAs(refTabcoin) {
                    end.linkTo(refTabCash.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }.padding(start = 5.dp, end = 20.dp),
            style = TextStyle(
                color = Colors.text,
                fontSize = Dimens.fontSizeOfSectionTitle
            )
        )

        Image(painter = painterResource(R.drawable.ic_tabcash),
            contentDescription = stringResource(R.string.tabcash),
            modifier = Modifier
                .size(10.dp)
                .constrainAs(refTabCashIcon) {
                    start.linkTo(refTabcoin.end)
                    end.linkTo(refTabCash.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }.clip(RoundedCornerShape(2.dp))
        )

        Text(
            "354",
            modifier = Modifier
                .constrainAs(refTabCash) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }.padding(start = 12.dp, end = 10.dp),
            style = TextStyle(
                color = Colors.text,
                fontSize = Dimens.fontSizeOfSectionTitle
            )
        )
    }
}
