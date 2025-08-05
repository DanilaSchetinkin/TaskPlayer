package com.example.taskplayer.core.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonMainButtom(
    onClick: () -> Unit,
    buttonColors: ButtonColors,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        modifier = Modifier
            .width(138.dp)
            .height(39.dp),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = buttonColors
    ) {
        content()
    }

}