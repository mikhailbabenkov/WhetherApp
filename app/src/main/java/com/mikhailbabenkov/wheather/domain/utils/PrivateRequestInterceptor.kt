package com.mikhailbabenkov.wheather.domain.utils

import okhttp3.Interceptor
import okhttp3.Response

class PrivateRequestInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().run {
            addHeader("x-api-key", "")
        }
        return chain.proceed(builder.build())
    }
}