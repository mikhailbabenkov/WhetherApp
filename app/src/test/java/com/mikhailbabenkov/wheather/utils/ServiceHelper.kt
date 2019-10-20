package com.mikhailbabenkov.wheather.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import com.mikhailbabenkov.wheather.domain.utils.ApiConfig
import com.mikhailbabenkov.wheather.domain.utils.DateDeserializer
import java.util.Date

object ServiceHelper {

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    private fun buildOkHttpClient(config: ApiConfig): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(config.connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(config.readWriteTimeout, TimeUnit.SECONDS)
            .writeTimeout(config.readWriteTimeout, TimeUnit.SECONDS)
            .build()
    }

    fun <T> getRetrofitService(clazz: Class<T>, config: ApiConfig): T = Retrofit.Builder()
        .baseUrl(config.apiUrl).client(
            buildOkHttpClient(config)
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(clazz)
}