package org.android.ticco.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.android.ticco.data.datasource.remote.auth.AuthRequest
import org.android.ticco.domain.usecase.RequestLoginUseCase
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val requestLoginUseCase: RequestLoginUseCase
) : ViewModel() {

    private val _isLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    fun requestLogin(body: AuthRequest) = viewModelScope.launch {
        _isLoggedIn.emit(requestLoginUseCase(body))
    }
}