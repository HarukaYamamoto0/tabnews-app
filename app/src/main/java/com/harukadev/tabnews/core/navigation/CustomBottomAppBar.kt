package com.harukadev.tabnews.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme

data class BottomNavigationItem(
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val router: Router
)

val items = listOf(
    BottomNavigationItem(
        title = R.string.recents,
        selectedIcon = R.drawable.house_fill,
        unselectedIcon = R.drawable.house,
        hasNews = false,
        badgeCount = null,
        RecentPostsRouter
    ), BottomNavigationItem(
        title = R.string.relevant,
        selectedIcon = R.drawable.ranking_fill,
        unselectedIcon = R.drawable.ranking,
        hasNews = false,
        badgeCount = null,
        router = RelevantPostsRouter
    )
)

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<BottomNavigationItem>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            CustomBarItem(item = item)
        }
    }
}

@Composable
fun CustomBarItem(modifier: Modifier = Modifier, item: BottomNavigationItem) {
    Box(
        modifier = modifier.height(36.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(item.selectedIcon),
            tint = Color.White,
            contentDescription = stringResource(item.title)
        )
    }
}

//
//@Composable
//fun CustomBottomAppBara(navController: NavHostController, modifier: Modifier = Modifier) {
//
//
//    var selectedItemIndex by rememberSaveable {
//        mutableIntStateOf(0)
//    }
//
//    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.tertiary,
//        tonalElevation = 5.dp,
//        modifier = modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(7.dp))
//            .height(64.dp)
//    ) {
//        items.forEachIndexed { index, item ->
//            NavigationBarItem(
//                modifier = Modifier.height(36.dp),
//                selected = selectedItemIndex == index,
//                onClick = {
//                    selectedItemIndex = index
//                    navController.navigate(item.router)
//                },
//                icon = {
//                    BadgedBox(badge = {
//                        if (item.badgeCount != null) {
//                            Badge {
//                                Text(item.badgeCount.toString())
//                            }
//                        } else if (item.hasNews) {
//                            Badge()
//                        }
//                    }) {
//                        Icon(
//                            imageVector = if (selectedItemIndex == index) item.selectedIcon
//                            else item.unselectedIcon, contentDescription = item.title
//                        )
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    indicatorColor = Color.Transparent,
//                    selectedIconColor = Color.White,
//                    unselectedIconColor = Color.White,
//                )
//            )
//        }
//    }
//}

@PreviewLightDark
@Composable
private fun CustomBottomAppBarPreview() {
    AppTheme {
        CustomBottomAppBar(
            navController = rememberNavController(),
            items = items
        )
    }
}