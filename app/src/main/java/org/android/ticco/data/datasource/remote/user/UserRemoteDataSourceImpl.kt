package org.android.ticco.data.datasource.remote.user

import org.android.ticco.domain.datasource.remote.AuthRemoteDataSource
import org.android.ticco.domain.datasource.remote.UserRemoteDataSource
import retrofit2.http.Header
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSourceImpl @Inject constructor(
    private val userApiService: UserApiService
) : UserRemoteDataSource {
    override suspend fun checkOnBoardingRequest(): CheckOnboardingResponse {
        return userApiService.checkOnboardingRequest()
    }

    override suspend fun updateProfile(image: File?, nickname: String): UpdateProfileResponse {
        return userApiService.updateProfile(image, nickname)
    }
}