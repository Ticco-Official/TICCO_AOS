package org.android.ticco.data.repository

import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.data.mapper.authResponseToToken
import org.android.ticco.data.mapper.tokenResponseToToken
import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import org.android.ticco.domain.model.Token
import org.android.ticco.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository{
    override suspend fun loginRequest(body: AuthRequest): Token =
        authRemoteDataSource.loginRequest(body).authResponseToToken()

    override suspend fun tokenRequest(body: TokenRequest): Token =
        authRemoteDataSource.tokenRequest(body).tokenResponseToToken()
}