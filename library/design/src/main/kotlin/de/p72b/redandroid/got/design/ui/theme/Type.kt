package de.p72b.redandroid.got.design.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import de.p72b.redandroid.got.design.R

val latoFamily = FontFamily(
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato, FontWeight.Normal),
    Font(R.font.lato, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.lato_bold, FontWeight.Bold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 4.sp,
        letterSpacing = 0.sp
    ),
    body2 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 3.sp,
        letterSpacing = 0.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        lineHeight = 2.sp,
        letterSpacing = 0.sp,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        lineHeight = 2.sp,
        letterSpacing = 0.sp,
        fontSize = 14.sp
    ),
    h1 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp
    ),
    h2 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        lineHeight = 6.sp,
        letterSpacing = 0.sp,
        fontSize = 20.sp
    )
)