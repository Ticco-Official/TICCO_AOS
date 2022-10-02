package org.android.ticco.data.repository

import org.android.ticco.data.mapper.toCheckOnBoarding
import org.android.ticco.domain.datasource.remote.UserRemoteDataSource
import org.android.ticco.domain.model.CheckOnboarding
import org.android.ticco.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository{
    override suspend fun checkOnBoardingRequest(): CheckOnboarding =
        userRemoteDataSource.checkOnBoardingRequest().toCheckOnBoarding()

    override suspend fun updateProfile(image: File?, nickname: String): Boolean =
        userRemoteDataSource.updateProfile(image, nickname).success
}