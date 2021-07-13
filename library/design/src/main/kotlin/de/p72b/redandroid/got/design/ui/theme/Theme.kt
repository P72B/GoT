package de.p72b.redandroid.got.design.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

fun colors(isDarkTheme: Boolean): Colors {
    return if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
}