package org.android.ticco.presentation.join

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.android.ticco.domain.usecase.mypage.EditUserInfoUserCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val editUserInfoUserCase: EditUserInfoUserCase
) : ViewModel() {

    private val _nickname: MutableStateFlow<String> = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname

    private val _profileImgUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val profileImgUri: StateFlow<Uri?> = _profileImgUri

    private val _isSuccess: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isSuccess: StateFlow<Boolean?> = _isSuccess

    private var profileImgMultiPart: MultipartBody.Part? = null

    fun setNickname(nickname: String) {
        viewModelScope.launch {
            _nickname.emit(nickname)
        }
    }

    fun setProfileImg(profileImgUri: Uri, file: File) {
        viewModelScope.launch {
            _profileImgUri.emit(profileImgUri)
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            profileImgMultiPart = MultipartBody.Part.createFormData("image", file.name, requestFile)
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            _isSuccess.emit(editUserInfoUserCase.editUserInfo(profileImgMultiPart, nickname.value))
        }
    }
}