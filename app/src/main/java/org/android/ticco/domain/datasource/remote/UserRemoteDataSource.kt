package org.android.ticco.domain.datasource.remote

import org.android.ticco.data.datasource.remote.user.CheckOnboardingResponse
import org.android.ticco.data.datasource.remote.user.UpdateProfileResponse
import java.io.File


interface UserRemoteDataSource {

    suspend fun checkOnBoardingRequest(): CheckOnboardingResponse

    suspend fun updateProfile(image: File?, nickname: String): UpdateProfileResponse
}