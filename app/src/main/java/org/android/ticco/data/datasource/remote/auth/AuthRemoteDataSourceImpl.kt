package org.android.ticco.data.datasource.remote.auth

import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun loginRequest(body: AuthRequest): AuthResponse {
        return authApiService.loginRequest(body)
    }

    override suspend fun tokenRequest(body: TokenRequest): TokenResponse {
        return authApiService.tokenRequest(body)
    }

}