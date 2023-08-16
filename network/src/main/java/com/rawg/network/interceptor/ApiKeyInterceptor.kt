package com.rawg.network.interceptor

import com.rawg.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrlBuilder = request.url.newBuilder()
            .addQueryParameter(
                "key",
                BuildConfig.API_KEY
            )
            .build()

        request = request.newBuilder().url(httpUrlBuilder).build()
        return chain.proceed(request)
    }
}