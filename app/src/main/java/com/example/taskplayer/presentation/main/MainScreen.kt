package com.example.taskplayer.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskplayer.R
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.data.remote.RetrofitClient
import com.example.taskplayer.domain.repository.AuthRepository
import com.example.taskplayer.presentation.main.feelings.FeelingItem
import com.example.taskplayer.presentation.main.quotes.QuotesItem
import com.example.taskplayer.presentation.main.components.BottomBar
import com.example.taskplayer.core.theme.DarkGreen
import com.example.taskplayer.core.theme.Grey
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.data.provider.AndroidResourceProvider
import com.example.taskplayer.presentation.main.components.MainAvatar


@Composable
fun MainScreen(tokenManager: UserSessionManager, navController: NavController) {
    val context = LocalContext.current
    val nickname = tokenManager.getNickName()
    val authService = RetrofitClient.authService
    val repository = remember { AuthRepository(authService) }
    val resources = remember { AndroidResourceProvider(context) }


    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory(repository, resources)
    )

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        containerColor = DarkGreen,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp)
                    .padding(horizontal = 30.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.tripalka),
                    contentDescription = "palki",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterStart)
                )
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "LogoMain",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
                MainAvatar(
                    tokenManager = tokenManager,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                )
            }
        },
        bottomBar = {
            BottomBar(
                currentRoute = "main",
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo("main") { inclusive = false }
                        launchSingleTop = true
                    }
                })
        }) { paddingValues ->
        MainScreenContent(
            paddingValues = paddingValues,
            nickname = nickname,
            viewModel = viewModel,

        )
    }
}

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    nickname: String,
    viewModel: MainViewModel
) {
    // Получаем все состояния из ViewModel
    val feelings by viewModel.feelings.collectAsState()
    val quotes by viewModel.quotes.collectAsState()
    val error by viewModel.error.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
    ) {

        error?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }


        Text(
            text = "С возвращением, $nickname!",
            style = MediaTheme.typography.alegreyaBoldTittle,
            color = MediaTheme.colors.text
        )

        Text(
            text = "Каким ты себя ощущаешь сегодня?",
            style = MediaTheme.typography.alegreyaSans20400,
            color = Grey
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Список чувств
        LazyRow {
            items(feelings) { feeling ->
                FeelingItem(feeling = feeling)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Список цитат
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            items(quotes) { quote ->
                QuotesItem(quotes = quote)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}