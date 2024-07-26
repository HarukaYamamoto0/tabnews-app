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
import androidx.navigation.toRoute
import com.harukadev.tabnews.data.Post
import com.harukadev.tabnews.ui.components.components.Header
import com.harukadev.tabnews.ui.components.screens.PostContentScreen
import com.harukadev.tabnews.ui.components.screens.RecentPostsScreen
import com.harukadev.tabnews.ui.components.screens.RelevantPostsScreen
import com.harukadev.tabnews.ui.theme.Colors
import com.harukadev.tabnews.ui.theme.TabNewsTheme
import kotlinx.serialization.Serializable

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

@Serializable
object RelevantPostsNavigationItem
@Serializable
object RecentPostsNavigationItem
@Serializable
data class PostContentNavigationItem(val author: String, val slug: String)

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
                NavHost(navController, startDestination = RelevantPostsNavigationItem) {
                    composable<RelevantPostsNavigationItem> { RelevantPostsScreen(navController) }
                    composable<RecentPostsNavigationItem> { RecentPostsScreen(navController) }
                    composable<PostContentNavigationItem> {
                        val postInfo = it.toRoute<PostContentNavigationItem>()
                        PostContentScreen(postInfo = postInfo)
                    }
                }
            }
        }
    }
}