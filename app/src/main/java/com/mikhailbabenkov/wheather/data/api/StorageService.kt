package com.mikhailbabenkov.wheather.data.api

interface StorageService {
    suspend fun <T>read(key: String): T?
    suspend fun <T>read(key: String, defaultValue: T): T
    suspend fun <T>write(key: String, value: T)
}