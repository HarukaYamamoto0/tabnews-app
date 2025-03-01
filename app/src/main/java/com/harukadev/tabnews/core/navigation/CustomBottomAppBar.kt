package com.harukadev.tabnews.core.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.R
import com.harukadev.tabnews.core.navigation.graphs.NotificationRouter
import com.harukadev.tabnews.core.navigation.graphs.RecentPostsRouter
import com.harukadev.tabnews.core.navigation.graphs.RelevantPostsRouter
import com.harukadev.tabnews.core.navigation.graphs.SettingsRouter
import com.harukadev.tabnews.ui.theme.AppTheme
import com.harukadev.tabnews.ui.theme.snow

data class BottomNavigationItem(
    val title: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val router: Any
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = R.string.recents,
        selectedIcon = R.drawable.house_fill,
        unselectedIcon = R.drawable.house,
        hasNews = false,
        badgeCount = null,
        RecentPostsRouter
    ),
    BottomNavigationItem(
        title = R.string.relevant,
        selectedIcon = R.drawable.ranking_fill,
        unselectedIcon = R.drawable.ranking,
        hasNews = false,
        badgeCount = null,
        router = RelevantPostsRouter
    ),
    BottomNavigationItem(
        title = R.string.notifications,
        selectedIcon = R.drawable.bell_fill,
        unselectedIcon = R.drawable.bell,
        hasNews = false,
        badgeCount = null,
        router = NotificationRouter
    ),
    BottomNavigationItem(
        title = R.string.settings,
        selectedIcon = R.drawable.user_circle_gear_fill,
        unselectedIcon = R.drawable.user_circle_gear,
        hasNews = false,
        badgeCount = null,
        router = SettingsRouter
    )
)

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    items: List<BottomNavigationItem>,
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 0.3.dp.toPx()
                )
            }
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(8.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.router)
                        }
                    )
            ) {
                val scale by animateFloatAsState(if (selectedItemIndex == index) 1.2f else 1f)

                Icon(
                    imageVector = ImageVector.vectorResource(if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon),
                    contentDescription = stringResource(item.title),
                    tint = snow,
                    modifier = Modifier
                        .size(36.dp)
                        .scale(scale)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CustomBottomAppBarPreview() {
    AppTheme {
        CustomBottomAppBar(
            navController = rememberNavController(), items = bottomNavigationItems
        )
    }
}