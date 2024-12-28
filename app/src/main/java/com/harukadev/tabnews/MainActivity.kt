package com.harukadev.tabnews

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.harukadev.tabnews.core.presentation.ObserverAsEvents
import com.harukadev.tabnews.core.presentation.toString
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsEvent
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsScreen
import com.harukadev.tabnews.posts.presentation.relevant_posts.RelevantPostsViewModel
import com.harukadev.tabnews.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) { innerPadding ->
                    val viewModel: RelevantPostsViewModel = koinViewModel()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    val context = LocalContext.current

                    ObserverAsEvents(viewModel.events) { event ->
                        when (event) {
                            is RelevantPostsEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    RelevantPostsScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )
                }
            }
        }
    }
}