package org.android.ticco.presentation.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.ticco.domain.model.User
import org.android.ticco.domain.usecase.mypage.EditUserInfoUserCase
import org.android.ticco.domain.usecase.mypage.GetUserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userUseCase: GetUserInfoUseCase,
    private val editUserInfoUserCase: EditUserInfoUserCase
): ViewModel(){

    val user = MutableLiveData<User>()

    var nickname = MutableLiveData<String>()
    var profileImage = MutableLiveData<String>()

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun setUserInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                userUseCase.getUserInfo()
            }.onSuccess {
                user.postValue(it)
            }
        }
    }

    fun editUserInfo() {
        viewModelScope.launch {
            _isSuccess.postValue(editUserInfoUserCase.editUserInfo(requireNotNull(nickname.value), requireNotNull(profileImage.value)))
        }
    }
}