package org.android.ticco.domain.datasource.remote

import org.android.ticco.data.datasource.remote.user.CheckOnboardingResponse


interface UserRemoteDataSource {

    suspend fun checkOnBoardingRequest(): CheckOnboardingResponse

}