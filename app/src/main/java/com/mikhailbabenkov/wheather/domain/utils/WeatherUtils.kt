package com.mikhailbabenkov.wheather.domain.utils

import java.text.DecimalFormat

object WeatherUtils {

    private const val KELVIN_OFFSET = 273.15f
    private val tempValueFormatter by lazy { DecimalFormat("#°") }

    fun convertToCel(temp: Float): String {
        return tempValueFormatter.format(temp - KELVIN_OFFSET)
    }

    fun composeImageLink(ref: String) = "http://openweathermap.org/img/w/$ref.png"

    fun parseWindDirection(deg: Float):String {
        return when {
            deg >= 337.5 -> "N"
            deg >= 22.5 && deg < 67.5 -> "NE"
            deg >= 67.5 && deg < 112.5 -> "E"
            deg >= 112.5 && deg < 157.5 -> "SE"
            deg >= 157.5 && deg < 202.5 -> "S"
            deg >= 202.5 && deg < 247.5 -> "SW"
            deg >= 247.5 && deg < 292.5 -> "W"
            deg >= 292.5 && deg < 337.5 -> "NW"
            else -> "UNKNOWN"
        }
    }
}