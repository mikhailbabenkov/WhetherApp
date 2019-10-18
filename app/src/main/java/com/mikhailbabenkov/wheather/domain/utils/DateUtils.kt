package com.mikhailbabenkov.wheather.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    private val LOCALE = Locale("en", "nz")
    private val API_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", LOCALE)
    private val DATE_ONLY_FORMAT = SimpleDateFormat("yyyy-MM-dd", LOCALE)
    private val TIME_ONLY_FORMAT = SimpleDateFormat("HH:mm", LOCALE)

    fun parseApiDate(date: String): Date {
        return try {
            API_DATE_FORMAT.parse(date) ?: throw NullPointerException("Recently nullable")
        } catch (e: ParseException) {
            throw e
        }
    }

    fun formatDateOnly(date: Date): String {
        return DATE_ONLY_FORMAT.format(date)
    }

    fun formatTimeOnly(date: Date): String {
        return TIME_ONLY_FORMAT.format(date)
    }
}