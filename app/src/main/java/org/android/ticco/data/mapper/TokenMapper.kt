package org.android.ticco.data.mapper

import org.android.ticco.data.datasource.remote.auth.AuthResponse
import org.android.ticco.data.datasource.remote.auth.TokenResponse
import org.android.ticco.domain.model.Token

fun AuthResponse.authResponseToToken(): Token =
    if (success) {
        Token(accessToken = data.token.accessToken, refreshToken = data.token.refreshToken)
    } else {
        Token(accessToken = null, refreshToken = null)
    }

fun TokenResponse.tokenResponseToToken(): Token =
    if (success) {
        Token(accessToken = data.accessToken, refreshToken = data.refreshToken)
    } else {
        Token(accessToken = null, refreshToken = null)
    }