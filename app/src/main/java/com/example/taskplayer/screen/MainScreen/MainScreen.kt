package com.example.taskplayer.screen.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskplayer.R
import com.example.taskplayer.data.local.TokenManager

@Composable
fun MainScreen(tokenManager: TokenManager){
//    Column(modifier = Modifier.fillMaxSize()) {
//        Text("MainScreen")
//        Button(onClick = {tokenManager.resetAllData()}  ) {
//            Text(text = "Сброс")
//        }
//    }
    Scaffold( topBar = {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Image(painter = painterResource(R.drawable.tripalka),
                contentDescription = "palki")
            Image(painter = painterResource(R.drawable.logo),
                contentDescription = "LogoMain")
            MainAvatar(tokenManager = tokenManager, modifier = Modifier.size(35.dp).clip(CircleShape))

        }
    },
        bottomBar = {

        }) {

    }

@Composable
fun MainScreenContent(){

    }
}