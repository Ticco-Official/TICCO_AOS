package org.android.ticco.data.repository

import okhttp3.MultipartBody
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.mapper.toCheckOnBoarding
import org.android.ticco.data.mapper.toEntity
import org.android.ticco.domain.datasource.remote.UserRemoteDataSource
import org.android.ticco.domain.model.CheckOnboarding
import org.android.ticco.domain.model.User
import org.android.ticco.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun checkOnBoardingRequest(): CheckOnboarding =
        userRemoteDataSource.checkOnBoardingRequest().toCheckOnBoarding()

    override suspend fun onBoardingRequest(): User =
        userRemoteDataSource.onBoardingRequest().toEntity()

    override suspend fun onBoardingPost(
        image: MultipartBody.Part?,
        nickname: String
    ): BasicResponse =
        userRemoteDataSource.onBoardingPost(image, nickname)
}