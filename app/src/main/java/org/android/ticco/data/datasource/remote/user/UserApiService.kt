package org.android.ticco.data.datasource.remote.user

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Query


interface UserApiService {

    @GET("/v1/user/onboarding/check")
    suspend fun checkOnboardingRequest(): CheckOnboardingResponse

    @GET("/v1/user/onboarding")
    suspend fun onboardingRequest(): UserResponse

    @PUT("/v1/user/onboarding?image=&nickname=")
    suspend fun onboardingPost(
        @Query("image") image: String?,
        @Query("nickname") nickname: String
    ): BasicResponse

}