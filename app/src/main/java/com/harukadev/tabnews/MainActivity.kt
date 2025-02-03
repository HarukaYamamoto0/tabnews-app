package com.harukadev.tabnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.harukadev.tabnews.core.navigation.AppNavHost
import com.harukadev.tabnews.core.navigation.CustomBottomAppBar
import com.harukadev.tabnews.core.navigation.CustomTopAppBar
import com.harukadev.tabnews.ui.theme.AppTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinContext {
                AppTheme {
                    val navController = rememberNavController()

                    Scaffold(
                        topBar = { CustomTopAppBar() },
                        bottomBar = {
                            CustomBottomAppBar(navController)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                    ) { innerPadding ->
                        AppNavHost(
                            navController = navController, modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}