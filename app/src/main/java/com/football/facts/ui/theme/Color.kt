package com.football.facts.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


private val Purple200 = Color(0xFFBB86FC)
private val Purple500 = Color(0xFF6200EE)
private val Purple700 = Color(0xFF3700B3)
private val Teal200 = Color(0xFF03DAC5)
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF000000)


class AppColors(
    val material: Colors,
    val textPrimary: Color ,
    val progressIndicatorColor : Color,
    val textSearch : Color
) {

    val primary: Color get() = material.primary
    val primaryVariant: Color get() = material.primaryVariant
    val secondary: Color get() = material.secondary
    val secondaryVariant: Color get() = material.secondaryVariant
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
    val isLight: Boolean get() = material.isLight
}

val LightColorPalette = AppColors(
    material = lightColors(
        background = White
    ),
    textPrimary = Black,
    progressIndicatorColor = Purple200,
    textSearch = Color.Blue
)

val DarkColorPalette = AppColors(
    material = darkColors(
        background = Black
    ),
    textPrimary = White ,
    progressIndicatorColor = White,
    textSearch = Color.Blue
)