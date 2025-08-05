package com.example.taskplayer.presentation.auth.component


import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.ui.text.TextStyle
import com.example.taskplayer.core.components.CommonButton
import com.example.taskplayer.core.theme.MediaTheme

@Composable
fun AuthButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MediaTheme.typography.alegreyaSans25,
    content: @Composable () -> Unit

) {
    CommonButton(
        onClick = onClick,
        buttonColors = ButtonColors(
            contentColor = MediaTheme.colors.text,
            containerColor = MediaTheme.colors.knopka,
            disabledContentColor = MediaTheme.colors.knopka,
            disabledContainerColor = MediaTheme.colors.knopka
        )
    ) {
        content()
    }
}