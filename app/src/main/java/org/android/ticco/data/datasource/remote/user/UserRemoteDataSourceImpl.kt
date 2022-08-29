package org.android.ticco.data.datasource.remote.user

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import org.android.ticco.domain.datasource.remote.UserRemoteDataSource
import retrofit2.http.Header
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRemoteDataSource {
    override suspend fun checkOnBoardingRequest(): CheckOnboardingResponse {
        return userApiService.checkOnboardingRequest()
    }

    override suspend fun onBoardingRequest(): UserResponse = userApiService.onboardingRequest()

    override suspend fun onBoardingPost(image: String?, nickname: String): BasicResponse = userApiService.onboardingPost(image, nickname)
}