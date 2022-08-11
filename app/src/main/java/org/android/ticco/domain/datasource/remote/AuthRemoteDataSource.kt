package org.android.ticco.domain.datasource.remote

import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.AuthResponse
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.data.datasource.remote.auth.TokenResponse


interface AuthRemoteDataSource {

    suspend fun loginRequest(body: AuthRequest): AuthResponse

    suspend fun tokenRequest(body: TokenRequest): TokenResponse
}