package com.presto.prezto.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PrestoDarkColorScheme = darkColorScheme(
    primary = NeonGreen,
    background = CharcoalBlack,
    surface = DarkGraySurface,
    onPrimary = CharcoalBlack,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun PreztoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PrestoDarkColorScheme,
        content = content
    )
}