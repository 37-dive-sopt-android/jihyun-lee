package com.sopt.dive.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDiveColors = staticCompositionLocalOf { defaultDiveColors }
val LocalDiveTypography = staticCompositionLocalOf { defaultDiveTypography }

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun DiveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        CompositionLocalProvider(
            LocalDiveColors provides defaultDiveColors,
            LocalDiveTypography provides defaultDiveTypography,
            content = content
        )
    }
}

object DiveTheme {
    val colors: DiveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDiveColors.current

    val typography: DiveTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDiveTypography.current
}