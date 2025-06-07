package com.example.svomnipro.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Primary,
    secondary = Secondary,
    onSecondary = Secondary,
    background = Color.White,
    surface = AquaBlue,
    error = Color.Red,
    onPrimaryContainer = Color.Black,
    onSecondaryContainer = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.Black
)

private val darkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Primary,
    secondary = Secondary,
    onSecondary = Secondary,
    background = Color.White,
    surface = AquaBlue,
    error = Color.Red,
    onPrimaryContainer = Color.Black,
    onSecondaryContainer = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.Black
)

@Composable
fun ThemeAppOmniProTest(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content,
        colorScheme = colors
    )
}