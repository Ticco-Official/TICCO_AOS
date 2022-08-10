package org.android.ticco.data.datasource.remote.user

import retrofit2.http.GET
import retrofit2.http.Header


interface UserApiService {

    @GET("/v1/user/onboarding/check")
    suspend fun checkOnboardingRequest(): CheckOnboardingResponse

}