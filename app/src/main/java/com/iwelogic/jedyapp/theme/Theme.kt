package com.iwelogic.jedyapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


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

data class Dimens(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp
)

val AppShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)


val LocalDimens = staticCompositionLocalOf { Dimens() }

@Composable
fun JedyAppTheme(
    dimens: Dimens = Dimens(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    CompositionLocalProvider(LocalDimens provides dimens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
            shapes = AppShapes
        )
    }
}