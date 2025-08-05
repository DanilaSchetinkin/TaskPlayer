package com.example.taskplayer.presentation.auth.component

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.taskplayer.core.components.CommonMainButtom
import com.example.taskplayer.core.theme.BlockButton
import com.example.taskplayer.core.theme.MediaTheme


/*для quotes кнопки*/
@Composable
fun MainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MediaTheme.typography.alegreyaSans25,
    content: @Composable () -> Unit
) {
    CommonMainButtom(
        onClick = onClick,
        buttonColors = ButtonColors(
            contentColor = MediaTheme.colors.text,
            containerColor = BlockButton,
            disabledContentColor = BlockButton,
            disabledContainerColor = BlockButton
        )
    ) {
        content()
    }

}