package org.android.ticco.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response
import org.android.ticco.data.datasource.local.SharedPreferences

class AuthInterceptor(private val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + preferences.accessToken).build()
        return chain.proceed(request)
    }
}