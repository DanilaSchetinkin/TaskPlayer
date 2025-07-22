package com.example.taskplayer.screen.MainScreen

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskplayer.R
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.screen.component.BottomBar
import com.example.taskplayer.ui.theme.DarkGreen
import com.example.taskplayer.ui.theme.MediaTheme


@Composable
fun MainScreen(tokenManager: TokenManager, navController: NavController) {
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text("MainScreen")
//        Button(onClick = {tokenManager.resetAllData()}  ) {
//            Text(text = "Сброс")
//        }
//    }
    val nickname = tokenManager.getNickName()

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
        MainScreenContent(paddingValues, nickname)
    }
}

@Composable
fun MainScreenContent(paddingValues: PaddingValues, nickname: String) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "С возвращением, $nickname!",
            style = MediaTheme.typography.alegreyaBoldTittle,
            color = MediaTheme.colors.text,
            modifier = Modifier.padding(paddingValues)
        )


        Text(
            text = "Каким ты себя ощущаешь сегодня?",
            style = MediaTheme.typography.alegreyaSans20400
        )
    }
}