package org.android.ticco.domain.model

data class Token(
    val accessToken: String?,
    val refreshToken: String?
)