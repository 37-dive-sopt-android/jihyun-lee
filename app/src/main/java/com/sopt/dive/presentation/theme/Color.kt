package com.sopt.dive.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val TransParent = Color(0x00FF0000)

val MainBlue = Color(0xFF456FFE)
val LimeGreen = Color(0xFF32CD32)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Error = Color(0xFFFF2222)

val Gray600 = Color(0xFF434343)
val Gray400 = Color(0xFF8C8C8C)
val Gray300 = Color(0xFFBFBFBF)
val Gray200 = Color(0xFFD9D9D9)

@Immutable
data class DiveColors(
    val white: Color,
    val black: Color,
    val transParent: Color,
    val mainBlue: Color,
    val limeGreen: Color,
    val purple80:Color,
    val purpleGrey80:Color,
    val pink80:Color,
    val purple40:Color,
    val purpleGrey40:Color,
    val pink40:Color,
    val error: Color,
    val gray600: Color,
    val gray400: Color,
    val gray300: Color,
    val gray200: Color,
)

val defaultDiveColors = DiveColors(
    white = White,
    black = Black,
    transParent = TransParent,
    mainBlue = MainBlue,
    limeGreen = LimeGreen,
    purple80 = Purple80,
    purpleGrey80 = PurpleGrey80,
    pink80 = Pink80,
    purple40 = Purple40,
    purpleGrey40 = PurpleGrey40,
    pink40 = Pink40,
    error = Error,
    gray600 = Gray600,
    gray400 = Gray400,
    gray300 = Gray300,
    gray200 = Gray200
)