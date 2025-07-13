package com.example.taskplayer.common

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.taskplayer.ui.theme.LightGreen
import com.example.taskplayer.ui.theme.MediaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    supportingText: @Composable () -> Unit = {},
    placeholder: @Composable () -> Unit = {}
){
    val interaction = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        onValueChange = {onChangeValue(it)},
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(LightGreen),
        visualTransformation = visualTransformation
    ){ innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = value,
            singleLine = true,
            innerTextField = innerTextField,
            enabled = true,
            visualTransformation = visualTransformation,
            interactionSource = interaction,
            isError = isError,
            supportingText = if(isError) supportingText else null,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGreen,
                disabledContainerColor = LightGreen,
                unfocusedTextColor = LightGreen,
                errorContainerColor = Color.Red,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red
            ),
        placeholder = placeholder
        )


    }
}