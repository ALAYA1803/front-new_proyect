package com.presto.prezto.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// Forzamos el Dark Mode para esa apariencia premium y tecnológica
private val PrestoDarkColorScheme = darkColorScheme(
    primary = NeonGreen,
    background = CharcoalBlack,
    surface = DarkGraySurface,
    onPrimary = CharcoalBlack, // El texto sobre los botones verdes será oscuro
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