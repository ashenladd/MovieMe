package com.example.movieme.feature.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieme.R


val cormorantFont = FontFamily(
    Font(R.font.cormorant_garamond_regular),
    Font(R.font.cormorant_garamond_medium),
    Font(R.font.cormorant_garamond_semibold),
    Font(R.font.cormorant_garamond_bold),
)

val lexendFont = FontFamily(
    Font(R.font.lexend_regular),
    Font(R.font.lexend_light),
    Font(R.font.lexend_medium),
    Font(R.font.lexend_semibold),
    Font(R.font.lexend_bold),
)

val TextAppearanceCormorantMovieMe = TextStyle(
    fontFamily = cormorantFont,
    color = Color.Black
)

val TextAppearanceCormorantMovieMeTitle1 = TextAppearanceCormorantMovieMe.copy(
    fontFamily = cormorantFont,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 24.sp
)

val TextAppearanceCormorantMovieMeTitle2 = TextAppearanceCormorantMovieMe.copy(
    fontFamily = cormorantFont,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 20.sp
)

val TextAppearanceCormorantMovieMeTitle3 = TextAppearanceCormorantMovieMe.copy(
    fontFamily = cormorantFont,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 20.sp
)

val TextAppearanceLexendMovieMe = TextStyle(
    fontFamily = lexendFont,
    color = Color.Black
)

val TextAppearanceLexendMovieMeTitle1 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    lineHeight = 24.sp
)

val TextAppearanceLexendMovieMeTitle2 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 20.sp
)

val TextAppearanceLexendMovieMeTitle3 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    lineHeight = 18.sp
)

val TextAppearanceLexendMovieMeBody1 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontSize = 12.sp,
    lineHeight = 12.sp
)

val TextAppearanceLexendMovieMeBody2 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontSize = 10.sp,
    lineHeight = 10.sp
)

val TextAppearanceLexendMovieMeSubtitle1 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontWeight = FontWeight.Light,
    fontSize = 12.sp,
    lineHeight = 12.sp
)

val TextAppearanceLexendMovieMeCaption1 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontSize = 10.sp,
    lineHeight = 10.sp
)

val TextAppearanceLexendMovieMeButton1 = TextAppearanceLexendMovieMe.copy(
    fontFamily = lexendFont,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 16.sp
)
