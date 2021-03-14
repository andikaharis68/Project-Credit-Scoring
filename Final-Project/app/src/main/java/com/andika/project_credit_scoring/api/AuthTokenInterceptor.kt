package com.andika.project_credit_scoring.api

import android.content.SharedPreferences
import com.andika.project_credit_scoring.Constanst.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(TOKEN, "GET TOKEN")
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().header("Authorization", "Bearer $token")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}