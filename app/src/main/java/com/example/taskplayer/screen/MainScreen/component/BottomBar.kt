package com.example.taskplayer.screen.MainScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskplayer.R

@Preview
@Composable
fun BottomBar(){
    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {}) { Icon(painter = painterResource(R.drawable.home), contentDescription = "homeBar") }
        IconButton(onClick = {}) { }
        IconButton(onClick = {}) { }
    }
}