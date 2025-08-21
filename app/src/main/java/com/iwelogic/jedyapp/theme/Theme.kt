package com.iwelogic.jedyapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Blue,
    tertiary = Blue,
    background = DarkBackground,
    primaryContainer = DarkPrimaryContainer,
    onBackground = Color.White,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = Color.White,
    onPrimaryContainer = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = Blue,
    tertiary = Blue,
    background = LightBackground,
    primaryContainer = LightPrimaryContainer,
    onBackground = Color.Black,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = Color.Black,
    onPrimaryContainer = Color.Black,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JedyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}