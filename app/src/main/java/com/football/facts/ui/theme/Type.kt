package com.football.facts.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp


private val H1 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 24.sp
)

private val H2 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 20.sp
)

private val H3 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    color = Color.Black,
    fontSize = 24.sp
)

val Typography = Typography(
    h1 = H1,
    h2 = H2,
    h3 = H3,
)