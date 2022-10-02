package org.android.ticco.domain.usecase.mypage

import okhttp3.MultipartBody
import org.android.ticco.data.mapper.getSuccess
import org.android.ticco.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditUserInfoUserCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun editUserInfo(image: MultipartBody.Part?, nickname: String): Boolean = userRepository.onBoardingPost(image, nickname).getSuccess()
}