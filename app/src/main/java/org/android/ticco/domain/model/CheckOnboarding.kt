package org.android.ticco.domain.model

data class CheckOnboarding(
    val status: Int,
    val message: String,
    val data: Check?
)

data class Check(
    val isChecked: Boolean
)