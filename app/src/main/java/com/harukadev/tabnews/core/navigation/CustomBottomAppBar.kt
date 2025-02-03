package com.harukadev.tabnews.core.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.R
import com.harukadev.tabnews.ui.theme.AppTheme

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val router: Router
)

@Composable
fun CustomBottomAppBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val items = listOf(
        BottomNavigationItem(
            title = stringResource(R.string.relevant),
            selectedIcon = ImageVector.vectorResource(R.drawable.ranking_fill),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ranking),
            hasNews = false,
            badgeCount = null,
            router = RelevantPostsRouter
        ),
        BottomNavigationItem(
            title = stringResource(R.string.recents),
            selectedIcon = ImageVector.vectorResource(R.drawable.clockwise_fill),
            unselectedIcon = ImageVector.vectorResource(R.drawable.clockwise),
            hasNews = false,
            badgeCount = null,
            RecentPostsRouter
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(selected = selectedItemIndex == index, onClick = {
                selectedItemIndex = index
                navController.navigate(item.router)
            }, label = {
                Text(item.title)
            }, icon = {
                BadgedBox(badge = {
                    if (item.badgeCount != null) {
                        Badge {
                            Text(item.badgeCount.toString())
                        }
                    } else if (item.hasNews) {
                        Badge()
                    }
                }) {
                    Icon(
                        imageVector = if (selectedItemIndex == index) item.selectedIcon
                        else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            })
        }
    }
}

@PreviewLightDark
@Composable
private fun CustomBottomAppBarPreview() {
    AppTheme {
        CustomBottomAppBar(navController = rememberNavController())
    }
}