package com.mikhailbabenkov.wheather.mock

import com.mikhailbabenkov.wheather.domain.utils.ApiConfig

class MockApiConfig: ApiConfig {
    override val connectionTimeout: Long
        get() = 1L
    override val readWriteTimeout: Long
        get() = 1L
    override val apiUrl: String
        get() = "http://localhost:21000"
}