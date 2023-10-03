package com.example.movieme.feature.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class DateParts(val year: Int, val month: String, val day: Int)

fun extractDatePartsFromDate(dateString: String): DateParts? {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(dateString)

        if (date != null) {
            val calendar = Calendar.getInstance()
            calendar.time = date

            val year = calendar.get(Calendar.YEAR)
            val month = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return DateParts(year, month, day)
        }

        return null // Handle parsing error
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}