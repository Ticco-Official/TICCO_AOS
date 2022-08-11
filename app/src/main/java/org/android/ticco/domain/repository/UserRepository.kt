package org.android.ticco.domain.repository

import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.data.datasource.remote.auth.TokenRequest
import org.android.ticco.domain.model.CheckOnboarding
import org.android.ticco.domain.model.Token


interface UserRepository {

    suspend fun checkOnBoardingRequest(): CheckOnboarding
}
