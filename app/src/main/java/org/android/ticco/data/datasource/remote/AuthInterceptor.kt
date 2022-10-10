package org.android.ticco.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response
import org.android.ticco.data.datasource.local.SharedPreferences

class AuthInterceptor(private val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJVU0VSX0lEIjozLCJleHAiOjE2OTY1MDQ5NjF9.4WRh-FseWmVVhgn6UuyfTDWo2LsOc-A65DoXkPjF6A5KdAXqwWDw2JptFqcVzlXgZ4S1jLz1hjaO95iWJljVZQ").build()

        return chain.proceed(request)
    }
}