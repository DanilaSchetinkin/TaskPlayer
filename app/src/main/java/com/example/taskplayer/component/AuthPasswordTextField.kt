package com.example.taskplayer.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import com.example.taskplayer.common.CommonTextField

@Composable
fun AuthPaswwordTextField(
    value: String,
    onChangeValue:(String)->Unit,
    isError: Boolean,
    supportingText: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    label: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        label()
        CommonTextField(
            value = value,
            onChangeValue = onChangeValue,
            isError = isError,
            supportingText = supportingText,
            placeholder = placeholder
        )
    }
}