package com.example.taskplayer.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.taskplayer.R
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme
import com.example.taskplayer.ui.theme.mediafontFamily

@Composable
fun HelloScreen(){

    Image(painter = painterResource(R.drawable.forest_les),
        contentDescription = null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(height = 199.dp, width = 191.dp))

            Text(text = "ПРИВЕТ", style = MediaTheme.typography.alegreyaBoldStart)
            Text(text ="Наслаждайся отборочными.", style = MediaTheme.typography.alegreyaSans20)
            Text(text = "Будь внимателен.", style = MediaTheme.typography.alegreyaSans20)
            Text(text = "Делай хорошо.", style = MediaTheme.typography.alegreyaSans20)

        Button(onClick = {}, colors =  ButtonDefaults.buttonColors(containerColor = LightGreen), shape = RoundedCornerShape(10.dp) ) {
            Text(text = "Войти в аккаунт", style = MediaTheme.typography.alegreyaSans25)
        }
    }
}


