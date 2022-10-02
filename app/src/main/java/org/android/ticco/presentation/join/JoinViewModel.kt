package org.android.ticco.presentation.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.android.ticco.domain.usecase.UpdateProfileUseCase
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    private val _nickname: MutableStateFlow<String> = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname
    private val _isSuccess: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isSuccess: StateFlow<Boolean?> = _isSuccess

    fun setNickname(nickname: String) {
        viewModelScope.launch {
            _nickname.emit(nickname)
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            _isSuccess.emit(updateProfileUseCase.invoke(null, nickname.value))
        }
    }
}