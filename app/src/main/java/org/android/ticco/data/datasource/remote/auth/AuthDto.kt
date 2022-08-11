package org.android.ticco.data.datasource.remote.auth

import com.google.gson.annotations.SerializedName

data class AuthRequest(

    @SerializedName("socialType")
    val socialType: String,
    @SerializedName("token")
    val token: String
)

data class AuthResponse(
    @SerializedName("data")
    val data: AuthData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)

data class TokenRequest(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)

data class TokenResponse(
    @SerializedName("data")
    val data: TokenData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)

data class AuthData(
    @SerializedName("token")
    val token: TokenData,
    @SerializedName("userId")
    val userId: Int
)

data class TokenData(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)