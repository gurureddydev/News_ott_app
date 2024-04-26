package com.guru.demoottapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import coil.compose.rememberImagePainter
import com.guru.demoottapp.screens.App
import com.guru.demoottapp.screens.home.HomeScreen
import com.guru.demoottapp.ui.theme.DemoOTTAppTheme
import com.guru.demoottapp.viewmodel.MainViewModel
import com.guru.domain_common.model.UserDM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            DemoOTTAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(androidx.tv.material3.MaterialTheme.colorScheme.onBackground)
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides androidx.tv.material3.MaterialTheme.colorScheme.errorContainer
                    ) {
                        App(
                            onBackPressed = onBackPressedDispatcher::onBackPressed,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    var isLoading by remember { mutableStateOf(false) }
    val userListState by viewModel.userListState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isLoading = true
                viewModel.refreshUsers()
            },
            enabled = !isLoading, // Disable the button when isLoading is true
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Fetch Users")
        }

        UserList(users = userListState.users)
    }
}


@Composable
fun UserList(users: List<UserDM>?) {
    LazyColumn {
        items(users ?: emptyList()) { user ->
            UserItem(user = user)
        }
    }
}

@Composable
fun UserItem(user: UserDM) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(user.image_url),
            contentDescription = "User Image",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        user.name?.let { Text(text = it) }
    }
}
