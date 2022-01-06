package com.example.practice.networking

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter(AUTH_PARAM, AUTH_KEY)
            .build()
        val authRequest = request.newBuilder().url(url).build()
        return chain.proceed(authRequest)
    }

    companion object {
        const val AUTH_PARAM = "appid"
        const val AUTH_KEY = "4d9d9224889825256ac730d3fd57912c"
    }
}
