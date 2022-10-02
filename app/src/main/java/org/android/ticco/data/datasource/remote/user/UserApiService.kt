package org.android.ticco.data.datasource.remote.user

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import retrofit2.http.*
import java.io.File


interface UserApiService {

    @GET("/v1/user/onboarding/check")
    suspend fun checkOnboardingRequest(): CheckOnboardingResponse

    @GET("/v1/user/onboarding")
    suspend fun onboardingRequest(): UserResponse

    @Multipart
    @PUT("/v1/user/onboarding")
    suspend fun onboardingPost(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part image: MultipartBody.Part?
    ): BasicResponse

}