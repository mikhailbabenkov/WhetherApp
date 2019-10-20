package com.mikhailbabenkov.wheather.utils

import java.io.File

enum class FakeResponse {
    Empty,
    Cities,
    Forecast;
    override fun toString(): String {
        return File("../app/src/test/api-response/$name.json").readText()
    }
}