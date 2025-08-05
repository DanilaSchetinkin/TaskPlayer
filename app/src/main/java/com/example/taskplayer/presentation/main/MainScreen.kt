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
import com.example.taskplayer.presentation.main.feelings.FeelingsViewModel
import com.example.taskplayer.presentation.main.feelings.FeelingsViewModelFactory
import com.example.taskplayer.presentation.main.quotes.QuotesItem
import com.example.taskplayer.presentation.main.quotes.QuotesViewModel
import com.example.taskplayer.presentation.main.quotes.QuotesViewModelFactory
import com.example.taskplayer.presentation.main.components.BottomBar
import com.example.taskplayer.core.theme.DarkGreen
import com.example.taskplayer.core.theme.Grey
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.presentation.main.components.MainAvatar


@Composable
fun MainScreen(tokenManager: UserSessionManager, navController: NavController) {

    val context = LocalContext.current
    val nickname = tokenManager.getNickName()
    val authService = RetrofitClient.authService
    val repository = remember { AuthRepository(authService) }
    val viewModel: FeelingsViewModel = viewModel(
        factory = FeelingsViewModelFactory(repository)
    )
    val quotesViewModel: QuotesViewModel = viewModel(
        factory = QuotesViewModelFactory(repository)
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
        MainScreenContent(paddingValues, nickname, viewModel, tokenManager, quotesViewModel)
    }
}

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    nickname: String,
    viewModel: FeelingsViewModel,
    tokenManager: UserSessionManager,
    quotesViewModel: QuotesViewModel
) {

    val feelings by viewModel.feelings.collectAsState()
    val quotes by quotesViewModel.quotes.collectAsState()

    Log.d("MainScreenContent", "MainScreenContent started")

    LaunchedEffect(Unit) {
        Log.d("MainScreenContent", "Calling loadFeelings()")
        viewModel.loadFeelings()
        quotesViewModel.loadQuotes()

    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
    ) {
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

        Log.d("MainScreenContent", "Feelings size: ${feelings.size}")


//        Column(modifier = Modifier.fillMaxSize()) {
//            Text("MainScreen")
//            Button(onClick = {tokenManager.resetAllData()}  ) {
//                Text(text = "Сброс")
//            }
//        }

        LazyRow {
            items(feelings) { feeling ->
                FeelingItem(feeling = feeling)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

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