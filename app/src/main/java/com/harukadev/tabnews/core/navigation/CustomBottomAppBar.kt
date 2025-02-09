package com.harukadev.tabnews.core.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            title = stringResource(R.string.recents),
            selectedIcon = ImageVector.vectorResource(R.drawable.house_fill),
            unselectedIcon = ImageVector.vectorResource(R.drawable.house),
            hasNews = false,
            badgeCount = null,
            RecentPostsRouter
        ),
        BottomNavigationItem(
            title = stringResource(R.string.relevant),
            selectedIcon = ImageVector.vectorResource(R.drawable.ranking_fill),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ranking),
            hasNews = false,
            badgeCount = null,
            router = RelevantPostsRouter
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        tonalElevation = 5.dp,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.router)
                },
                label = {
                    Text(item.title)
                },
                icon = {
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
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                )
            )
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