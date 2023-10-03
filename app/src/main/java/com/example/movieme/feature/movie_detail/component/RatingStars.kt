package com.example.movieme.feature.movie_detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating10: Double,
    maxRating: Int = 5,
    ratingColor: Color = Color(0xFFFDD147),
) {
    val rating5 = rating10 / 2
    val fullStars = rating5.toInt()

    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            val iconColor = when {
                i <= fullStars -> ratingColor
                else -> Color.Gray
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}
