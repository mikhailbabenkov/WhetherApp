package com.mikhailbabenkov.wheather.data.api

import android.content.Context
import io.paperdb.Paper

class StorageServiceImpl(context: Context): StorageService {

    init {
        Paper.init(context)
    }

    override suspend fun <T> read(key: String, defaultValue: T): T {
        return Paper.book().read<T>(key, defaultValue)
    }

    override suspend fun <T> read(key: String): T? {
        return Paper.book().read<T>(key)
    }

    override suspend fun <T> write(key: String, value: T) {
        Paper.book().write(key, value)
    }
}