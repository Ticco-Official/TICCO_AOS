package org.android.ticco.domain.usecase.mypage

import org.android.ticco.data.mapper.getSuccess
import org.android.ticco.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditUserInfoUserCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun editUserInfo(image: String, nickname: String): Boolean = userRepository.onBoardingPost(image, nickname).getSuccess()
}