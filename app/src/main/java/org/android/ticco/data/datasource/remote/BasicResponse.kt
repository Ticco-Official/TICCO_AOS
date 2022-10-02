package org.android.ticco.data.datasource.remote

data class BasicResponse(
    val data: Any,
    val message: String,
    val status: Int,
    val success: Boolean
)