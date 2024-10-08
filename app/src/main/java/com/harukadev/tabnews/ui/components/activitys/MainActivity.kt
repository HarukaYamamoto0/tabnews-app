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
import com.harukadev.tabnews.data.PostContent
import com.harukadev.tabnews.ui.components.components.BottomNavigationCustom
import com.harukadev.tabnews.ui.components.components.Header
import com.harukadev.tabnews.ui.components.components.HomeRoute
import com.harukadev.tabnews.ui.components.components.NotificationsRoute
import com.harukadev.tabnews.ui.components.components.PostContentRoute
import com.harukadev.tabnews.ui.components.components.RecentPostsRoute
import com.harukadev.tabnews.ui.components.components.SettingsRoute
import com.harukadev.tabnews.ui.components.screens.NotificationsScreen
import com.harukadev.tabnews.ui.components.screens.PostContentScreen
import com.harukadev.tabnews.ui.components.screens.RecentPostsScreen
import com.harukadev.tabnews.ui.components.screens.RelevantPostsScreen
import com.harukadev.tabnews.ui.components.screens.SettingsScreen
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

@Composable
fun App() {
    TabNewsTheme {
        val navController = rememberNavController()

        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigationCustom(navController) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.background)
                    .padding(innerPadding)
            ) {
                Header()
                NavHost(
                    navController,
                    startDestination = HomeRoute.route
                ) {

                    composable(HomeRoute.route) { RelevantPostsScreen(navController) }
                    composable(RecentPostsRoute.route) { RecentPostsScreen(navController) }
                    composable(NotificationsRoute.route) { NotificationsScreen(navController) }
                    composable(SettingsRoute.route) { SettingsScreen(navController) }
                    composable<PostContentRoute> {
                        val args = it.toRoute<PostContentRoute>()
                        PostContentScreen(args)
                    }
                }
            }
        }
    }
}