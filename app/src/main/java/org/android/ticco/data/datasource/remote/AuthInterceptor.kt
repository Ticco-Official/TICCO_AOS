package org.android.ticco.data.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response
import org.android.ticco.data.datasource.local.SharedPreferences

class AuthInterceptor(private val preferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJVU0VSX0lEIjoyLCJleHAiOjE2OTM2NDU1MjZ9.700alh5J2rgSYJ0p-rmrixLWMvKppBgD5cf30pKlyNDPV8laRcEdTv6V1QZ2zdYUIyCNAWDtFmtbhVzLc-c3Qw").build()

        return chain.proceed(request)
    }
}