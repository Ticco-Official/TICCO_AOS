package org.android.ticco.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(image: File?, nickname: String): Boolean = withContext(Dispatchers.IO) {
        val isSuccess = userRepository.updateProfile(image, nickname)
        return@withContext isSuccess
    }
}