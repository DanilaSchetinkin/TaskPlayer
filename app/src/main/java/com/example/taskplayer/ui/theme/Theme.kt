package com.example.taskplayer.ui.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskplayer.R
import org.jetbrains.annotations.Blocking

@Immutable
data class MediaColors(
    val block: Color,
    val text: Color,
    val hint: Color,
    val knopka: Color
)

@Immutable
data class MediaTextStyle(
    val alegreyaBoldStart: TextStyle,
    val alegreyaBoldTittle:TextStyle,
    val alegreya25: TextStyle,
    val alegreyaSans25: TextStyle,
    val alegreyaSans22: TextStyle,
    val alegreyaSans20: TextStyle,
    val alegreyaSans20400: TextStyle,
    val alegreyaSans18: TextStyle,
    val alegreyaSans15: TextStyle,
    val alegreyaSans12: TextStyle

)

val LocalMediaTypography = staticCompositionLocalOf {
    MediaTextStyle(
        alegreyaBoldStart = TextStyle.Default,
        alegreyaBoldTittle = TextStyle.Default,
        alegreya25 = TextStyle.Default ,
        alegreyaSans25 = TextStyle.Default ,
        alegreyaSans22 = TextStyle.Default,
        alegreyaSans20 = TextStyle.Default,
        alegreyaSans20400 = TextStyle.Default,
        alegreyaSans18 = TextStyle.Default,
        alegreyaSans15 = TextStyle.Default,
        alegreyaSans12 = TextStyle.Default
    )
}

val LocalMediaColors = staticCompositionLocalOf {
    MediaColors(
        block = Color.Unspecified,
        text = Color.Unspecified,
        hint = Color.Unspecified,
        knopka = Color.Unspecified
    )
}

val mediafontFamily = FontFamily(
    Font(R.font.alegreya_extra_bold, FontWeight.W500),
)

val sansMediaFontFamily = FontFamily(
    Font(R.font.alegreya_regular, FontWeight.W400)
)

val sansMediaFontFamily500 = FontFamily(
    Font(R.font.alegreya_bold, FontWeight.W500)
)

@Composable
fun MediaTheme( content: @Composable ()-> Unit){
    val mediaColors = MediaColors(
        block = Color(0xFF253334),
        text = Color(0xFFFFFFFF),
        hint = Color(0xFFBEC2C2),
        knopka = Color(0xFF7C9A92)

    )

    val mediaTypography = MediaTextStyle(
        alegreyaBoldStart = TextStyle(fontFamily = mediafontFamily, fontWeight = FontWeight.W500, fontSize = 34.sp),
        alegreyaBoldTittle = TextStyle(fontFamily = mediafontFamily, fontWeight = FontWeight.W500, fontSize = 30.sp),
        alegreya25 = TextStyle(fontFamily = mediafontFamily, fontWeight = FontWeight.W500, fontSize = 25.sp ),
        alegreyaSans25 = TextStyle(fontFamily = sansMediaFontFamily500, fontWeight = FontWeight.W500, fontSize = 25.sp),
        alegreyaSans22 = TextStyle(fontFamily = sansMediaFontFamily, fontWeight = FontWeight.W400, fontSize = 22.sp),
        alegreyaSans20 = TextStyle(fontFamily = sansMediaFontFamily500, fontWeight = FontWeight.W500, fontSize = 20.sp),
        alegreyaSans20400 = TextStyle(fontFamily = sansMediaFontFamily, fontWeight = FontWeight.W400, fontSize = 20.sp),
        alegreyaSans18 = TextStyle(fontFamily = sansMediaFontFamily, fontWeight = FontWeight.W400, fontSize = 18.sp),
        alegreyaSans15 = TextStyle(fontFamily = sansMediaFontFamily500, fontWeight = FontWeight.W500, fontSize = 15.sp),
        alegreyaSans12 = TextStyle(fontFamily = sansMediaFontFamily, fontWeight = FontWeight.W400, fontSize = 12.sp)
    )

    CompositionLocalProvider(
        LocalMediaColors provides mediaColors,
        LocalMediaTypography provides mediaTypography)
    {
        content()
    }

}

object MediaTheme{
    val colors: MediaColors
    @Composable
    get() = LocalMediaColors.current

    val  typography: MediaTextStyle
    @Composable
    get() = LocalMediaTypography.current
}
