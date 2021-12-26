package com.football.facts.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

val H1 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    color = Black,
    fontSize = 30.sp
)

val Body1 = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val Typography = Typography(
    body1 = Body1,
    h1 = H1
)