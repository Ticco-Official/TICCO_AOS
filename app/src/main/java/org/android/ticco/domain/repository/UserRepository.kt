package org.android.ticco.domain.repository

import okhttp3.MultipartBody
import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import org.android.ticco.domain.model.CheckOnboarding
import org.android.ticco.domain.model.Token
import org.android.ticco.domain.model.User
import java.io.File


interface UserRepository {

    suspend fun checkOnBoardingRequest(): CheckOnboarding

    suspend fun onBoardingRequest(): User

    suspend fun onBoardingPost(image: MultipartBody.Part?, nickname: String): BasicResponse
}
