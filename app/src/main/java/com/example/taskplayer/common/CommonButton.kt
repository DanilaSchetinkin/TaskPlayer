package com.example.taskplayer.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskplayer.ui.theme.MediaTheme

@Composable
fun CommonButton(onClick: ()-> Unit,
                 buttonColors: ButtonColors,
                 modifier: Modifier = Modifier,
                 content: @Composable () -> Unit) {
    Button(
        modifier = Modifier
            .width(321.dp)
        .height(61.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = buttonColors
    ) {
        content()
    }
}

