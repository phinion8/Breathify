package com.phinion.breathify.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = white,
    secondary = black,
    tertiary = blue,
    background = black,
    onPrimary = white,
    onSecondary = white,
    onTertiary = white,
    onBackground = white,
    onSurface = Color(0xFF1C1B1F)
    )

private val LightColorScheme = lightColorScheme(
    primary = black,
    secondary = white,
    tertiary = blue,
    background = white,
    surface = Color(0xFFFFFBFE),
    onPrimary = black,
    onSecondary = black,
    onTertiary = black,
    onBackground = black,
    onSurface = lightGrey
)
@Composable
fun BreathingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            window.navigationBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}