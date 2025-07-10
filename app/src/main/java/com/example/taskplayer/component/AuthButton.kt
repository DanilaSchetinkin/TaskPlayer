package com.example.taskplayer.component


import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.ui.text.TextStyle
import com.example.taskplayer.common.CommonButton
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme

@Composable
fun AuthButton(
    onClick: ()-> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MediaTheme.typography.alegreyaSans25,
    content: @Composable () -> Unit

){
    CommonButton(onClick = onClick,
        buttonColors = ButtonColors(
            contentColor = MediaTheme.colors.text,
            containerColor = MediaTheme.colors.knopka,
            disabledContentColor = MediaTheme.colors.knopka,
            disabledContainerColor = MediaTheme.colors.knopka
        )
         ){
        content()
    }
}