package org.android.ticco.domain.datasource.remote

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.CheckOnboardingResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse


interface UserRemoteDataSource {

    suspend fun checkOnBoardingRequest(): CheckOnboardingResponse

    suspend fun onBoardingRequest(): UserResponse

    suspend fun onBoardingPost(image: String?, nickname: String): BasicResponse

}