package com.harukadev.tabnews.ui.components.activitys

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.data.fakeData
import com.harukadev.tabnews.ui.components.fragments.RecentPostsFragment
import com.harukadev.tabnews.ui.components.fragments.RelevantPostsFragment
import com.harukadev.tabnews.ui.components.components.Header
import com.harukadev.tabnews.ui.components.fragments.PostContentFragment
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.TabNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(Colors.onBackground.toArgb())
            )
        } else {
            window.statusBarColor = Colors.onBackground.toArgb()
        }
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true, apiLevel = 35)
@Composable
fun AppPreview() {
    App()
}

sealed class NavigationItem(val route: String) {
    data object RelevantPosts : NavigationItem("relevantPosts")
    data object RecentPosts : NavigationItem("recentPosts")
    data object PostContent : NavigationItem("postContent/{postJson}")
}

@Composable
fun App() {
    TabNewsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.background)
                    .padding(innerPadding)
            ) {
                val navController = rememberNavController()
                Header(navController = navController)
                NavHost(navController, startDestination = "relevantPosts") {
                    composable(NavigationItem.RelevantPosts.route) {
                        RelevantPostsFragment(
                            navController
                        )
                    }
                    composable(NavigationItem.RecentPosts.route) { RecentPostsFragment(navController) }
                    composable(NavigationItem.PostContent.route) { backStackEntry ->
                        PostContentFragment(backStackEntry)
                    }
                }
            }
        }
    }
}

