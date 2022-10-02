package org.android.ticco.data.datasource.remote.user

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PUT
import java.io.File


interface UserApiService {

    @GET("/v1/user/onboarding/check")
    suspend fun checkOnboardingRequest(): CheckOnboardingResponse

    @FormUrlEncoded
    @PUT("/v1/user/onboarding")
    suspend fun updateProfile(@Field("image") image: File?, @Field("nickname") nickname: String): UpdateProfileResponse
}