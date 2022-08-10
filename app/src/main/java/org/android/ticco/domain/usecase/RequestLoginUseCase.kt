package org.android.ticco.domain.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.android.ticco.TiccoApplication
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.domain.model.Token
import org.android.ticco.domain.repository.AuthRepository
import org.android.ticco.domain.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
){
    suspend operator fun invoke(body: AuthRequest): Boolean = withContext(Dispatchers.IO) {
        val token = authRepository.loginRequest(body)
        if (token.accessToken == null){
            throw Exception()
        }
        TiccoApplication.preferences.accessToken = token.accessToken
        TiccoApplication.preferences.refreshToken = token.refreshToken

        val check = userRepository.checkOnBoardingRequest()
        return@withContext check.data?.isChecked == true
        //userRepository.checkOnBoardingRequest().data?.isChecked == true
    }
}