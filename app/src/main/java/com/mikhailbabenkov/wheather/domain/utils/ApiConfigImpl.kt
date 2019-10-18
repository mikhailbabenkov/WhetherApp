package com.mikhailbabenkov.wheather.domain.utils

class ApiConfigImpl: ApiConfig {
    override val connectionTimeout: Long
        get() = 10L
    override val readWriteTimeout: Long
        get() = 10L
    override val apiUrl: String
        get() = "http://api.openweathermap.org/data/2.5/"
}