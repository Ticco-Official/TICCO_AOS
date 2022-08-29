package org.android.ticco.domain.repository

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import org.android.ticco.domain.model.CheckOnboarding
import org.android.ticco.domain.model.Token
import org.android.ticco.domain.model.User


interface UserRepository {

    suspend fun checkOnBoardingRequest(): CheckOnboarding

    suspend fun onBoardingRequest(): User

    suspend fun onBoardingPost(image: String?, nickname: String): BasicResponse
}
