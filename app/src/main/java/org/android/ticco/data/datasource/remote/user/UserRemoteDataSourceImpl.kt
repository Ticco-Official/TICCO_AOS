package org.android.ticco.data.datasource.remote.user

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
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

    override suspend fun onBoardingRequest(): UserResponse = userApiService.onboardingRequest()

    override suspend fun onBoardingPost(image: MultipartBody.Part?, nickname: String): BasicResponse {
        val map = mutableMapOf<String, @JvmSuppressWildcards RequestBody>()
        map["nickname"] = nickname.toRequestBody("text/plain".toMediaTypeOrNull())
        return userApiService.onboardingPost(map, image)
    }
}