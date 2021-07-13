package de.p72b.redandroid.gothouses

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import de.p72b.redandroid.got.design.ui.theme.Shapes
import de.p72b.redandroid.got.design.ui.theme.Typography
import de.p72b.redandroid.got.design.ui.theme.colors

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors(darkTheme),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}