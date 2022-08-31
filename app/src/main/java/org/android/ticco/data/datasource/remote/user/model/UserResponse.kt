package org.android.ticco.data.datasource.remote.user.model

data class UserResponse(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val nickname: String,
        val profileImageUrl: String
    )
}