package org.android.ticco.data.datasource.remote.auth

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/v1/auth/login")
    suspend fun loginRequest(@Body body: AuthRequest): AuthResponse

    @POST("/v1/auth/refresh")
    suspend fun tokenRequest(@Body body: TokenRequest): TokenResponse
}