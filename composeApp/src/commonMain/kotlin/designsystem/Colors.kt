package designsystem

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Red500 = Color(255, 80, 80, 255)
val Orange800 = Color(247, 205, 70, 255)
val Orange500 = Color(245, 205, 74, 255)
val Gray100 = Color(171, 158, 117, 255)
val White300 = Color(230, 230, 230, 255)
val WhiteTransparent = Color(230, 230, 230, 50)

@Composable
fun LightTheme(): Colors {
    return Colors(
        primary = Red500,
        primaryVariant = Orange800,
        secondary = Orange800,
        secondaryVariant = Color.Yellow,
        background = MaterialTheme.colors.background,
        surface = MaterialTheme.colors.surface,
        error = MaterialTheme.colors.error,
        onPrimary = MaterialTheme.colors.onPrimary,
        onSecondary = MaterialTheme.colors.onSecondary,
        onBackground = MaterialTheme.colors.onBackground,
        onSurface = MaterialTheme.colors.onSurface,
        onError = MaterialTheme.colors.onError,
        isLight = MaterialTheme.colors.isLight
    )
}

@Composable
fun DarkTheme(): Colors {
    return Colors(
        primary = Orange800,
        primaryVariant = Orange800,
        secondary = Red500,
        secondaryVariant = Color.Yellow,
        background = MaterialTheme.colors.background,
        surface = MaterialTheme.colors.surface,
        error = MaterialTheme.colors.error,
        onPrimary = MaterialTheme.colors.onPrimary,
        onSecondary = MaterialTheme.colors.onSecondary,
        onBackground = MaterialTheme.colors.onBackground,
        onSurface = MaterialTheme.colors.onSurface,
        onError = MaterialTheme.colors.onError,
        isLight = MaterialTheme.colors.isLight
    )
}