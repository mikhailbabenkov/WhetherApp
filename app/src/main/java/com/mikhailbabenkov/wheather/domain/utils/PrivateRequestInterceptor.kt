package com.mikhailbabenkov.wheather.domain.utils

import com.mikhailbabenkov.wheather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class PrivateRequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.url.newBuilder().run {
            addQueryParameter("APPID", BuildConfig.APP_ID)
        }.build()
        return chain.proceed(request.newBuilder().url(builder).build())
    }
}