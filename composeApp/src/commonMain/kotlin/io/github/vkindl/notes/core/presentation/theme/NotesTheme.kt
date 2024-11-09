package io.github.vkindl.notes.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
internal fun NotesTheme(
    colorScheme: ColorScheme = NotesThemeThemeDefaults.colorScheme,
    shapes: Shapes = NotesThemeThemeDefaults.shapes,
    typography: Typography = NotesThemeThemeDefaults.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimens provides Dimensions()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
}

internal object NotesThemeThemeDefaults {

    val colorScheme: ColorScheme
        @Composable get() = if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors

    val shapes: Shapes
        @Composable get() = MaterialTheme.shapes

    val typography: Typography
        @Composable get() = MaterialTheme.typography
}
