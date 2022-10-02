package org.android.ticco.domain.repository
import org.android.ticco.domain.model.CheckOnboarding
import java.io.File


interface UserRepository {

    suspend fun checkOnBoardingRequest(): CheckOnboarding

    suspend fun updateProfile(image: File?, nickname: String): Boolean
}
