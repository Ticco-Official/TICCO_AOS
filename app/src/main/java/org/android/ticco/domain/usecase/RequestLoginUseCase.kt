package org.android.ticco.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.domain.model.Token
import org.android.ticco.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(body: AuthRequest): Token = withContext(Dispatchers.IO) {
        authRepository.loginRequest(body)
    }
}