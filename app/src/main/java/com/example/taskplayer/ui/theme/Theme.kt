package com.example.taskplayer.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import org.jetbrains.annotations.Blocking

@Immutable
data class MediaColors(
    val block: Color,
    val text: Color,
    val hint: Color
)

val LocalMediaColors = staticCompositionLocalOf {
    MediaColors(
        block = Color.Unspecified,
        text = Color.Unspecified,
        hint = Color.Unspecified
    )
}

@Composable
fun MediaTheme( content: @Composable ()-> Unit){
    val mediaColors = MediaColors(
        block = Color(0xFF253334),
        text = Color(0xFFFFFFFF),
        hint = Color(0xFFBEC2C2)
    )
    CompositionLocalProvider() {
        LocalMediaColors provides mediaColors
    }
    content()
}

object MediaTheme{
    val colors: MediaColors
    @Composable
    get() = LocalMediaColors.current
}
