package org.android.ticco.domain.datasource.remote

import okhttp3.MultipartBody
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.CheckOnboardingResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import java.io.File


interface UserRemoteDataSource {

    suspend fun checkOnBoardingRequest(): CheckOnboardingResponse

    suspend fun onBoardingRequest(): UserResponse

    suspend fun onBoardingPost(image: MultipartBody.Part?, nickname: String): BasicResponse

}