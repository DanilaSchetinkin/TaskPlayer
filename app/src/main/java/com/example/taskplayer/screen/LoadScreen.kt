package com.example.taskplayer.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.taskplayer.R

@Composable
fun LoadScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        // задник
        Image(painter = painterResource(R.drawable.forest_les),
            contentDescription = null,
            modifier = Modifier.fillMaxSize())
    }
        // лого
    Image(painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = Modifier.fillMaxSize())
}