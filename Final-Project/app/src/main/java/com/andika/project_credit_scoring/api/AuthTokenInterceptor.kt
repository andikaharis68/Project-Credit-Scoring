package com.andika.project_credit_scoring.api

import android.content.SharedPreferences
import android.util.Log
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(TOKEN, "GET TOKEN")
        Log.d("TOKEN", "$token")
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder().header("Authorization", "Bearer $token")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}