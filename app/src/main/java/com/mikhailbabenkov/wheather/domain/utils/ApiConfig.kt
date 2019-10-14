package com.mikhailbabenkov.wheather.domain.utils

interface ApiConfig {
    val apiUrl: String
    val connectionTimeout: Long
    val readWriteTimeout: Long
}