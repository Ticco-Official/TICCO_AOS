package org.android.ticco.domain.repository

import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.domain.model.Token


interface AuthRepository {
    suspend fun loginRequest(body: AuthRequest): Token

    suspend fun tokenRequest(body: TokenRequest): Token
}
