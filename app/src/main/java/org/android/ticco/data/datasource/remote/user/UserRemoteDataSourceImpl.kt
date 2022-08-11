package org.android.ticco.data.datasource.remote.user

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
}