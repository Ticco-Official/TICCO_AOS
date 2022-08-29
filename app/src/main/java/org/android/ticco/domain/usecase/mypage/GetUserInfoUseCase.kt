package org.android.ticco.domain.usecase.mypage

import org.android.ticco.domain.model.User
import org.android.ticco.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getUserInfo():User = userRepository.onBoardingRequest()
}