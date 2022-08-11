package org.android.ticco.data.datasource.remote.user

import com.google.gson.annotations.SerializedName

data class CheckOnboardingResponse(
    @SerializedName("data")
    val data: Checkdata,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)

data class Checkdata(
    @SerializedName("isChecked")
    val isChecked: Boolean
)