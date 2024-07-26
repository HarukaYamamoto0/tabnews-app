package com.harukadev.tabnews.ui.components.components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.components.activitys.NavigationItem
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.Dimens
import com.harukadev.tabnews.utils.composables.bounceClick
import com.harukadev.tabnews.ui.components.activitys.NavigationItem.RelevantPosts.route as route1

@Composable
fun Header(navController: NavController) {
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

        var fragmentRouteActive by rememberSaveable { mutableStateOf(NavigationItem.RelevantPosts.route) }
        NavController.OnDestinationChangedListener { _: NavController, navDestination: NavDestination, _: Bundle? ->
            run {
                fragmentRouteActive = navDestination.route.toString()
            }
        }

        val (refIcon, refRelevantText, refRecentTextRef, refMenu) = createRefs()

        Image(painter = painterResource(R.drawable.tabnews),
            contentDescription = "Icon",
            modifier = Modifier
                .size(48.dp)
                .constrainAs(refIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(refRelevantText.start)
                }
                .bounceClick { context.startActivity(intent) })

        Text(text = stringResource(id = R.string.textView_toolbar_relevant),
            color = Colors.text,
            textDecoration = if (fragmentRouteActive == NavigationItem.RelevantPosts.route) TextDecoration.Underline else TextDecoration.None,
            modifier = Modifier
                .constrainAs(refRelevantText) {
                    start.linkTo(refIcon.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(refRecentTextRef.start)
                }
                .padding(0.dp)
                .padding(start = 15.dp)
                .bounceClick {
                    fragmentRouteActive = NavigationItem.RelevantPosts.route
                    navController.navigate(NavigationItem.RelevantPosts.route)
                },
            fontSize = Dimens.fontSizeOfSectionTitle
        )

        Text(text = stringResource(id = R.string.textView_toolbar_recent),
            color = Colors.text,
            textDecoration = if (fragmentRouteActive == NavigationItem.RecentPosts.route) TextDecoration.Underline else TextDecoration.None,
            modifier = Modifier
                .constrainAs(refRecentTextRef) {
                    start.linkTo(refRelevantText.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp)
                .padding(start = 15.dp)
                .bounceClick {
                    fragmentRouteActive = NavigationItem.RecentPosts.route
                    navController.navigate(NavigationItem.RecentPosts.route)
                },
            fontSize = Dimens.fontSizeOfSectionTitle
        )


        Image(painter = painterResource(R.drawable.menu),
            contentDescription = "Icon",
            modifier = Modifier
                .size(24.dp)
                .constrainAs(refMenu) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .bounceClick { })
    }
}
