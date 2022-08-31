package org.android.ticco.presentation.mypage

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.android.ticco.domain.usecase.mypage.EditUserInfoUserCase
import org.android.ticco.domain.usecase.mypage.GetUserInfoUseCase
import org.android.ticco.presentation.util.Event
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userUseCase: GetUserInfoUseCase,
    private val editUserInfoUserCase: EditUserInfoUserCase
) : ViewModel() {

    private var oldNickname = MutableLiveData<String>()
    var nickname = MutableLiveData<String>()

    private val _isSuccess = MutableLiveData<Event<Boolean>>()
    val isSuccess: LiveData<Event<Boolean>> = _isSuccess

    private var oldProfileImgUri = MutableLiveData<Uri>()
    val profileImgUri = MutableLiveData<Uri?>()

    private var profileImageMultiPart: MultipartBody.Part? = null

    val isModify = MediatorLiveData<Boolean>().apply {
        addSource(profileImgUri) { value = isValidModify() }
        addSource(nickname) { value = isValidModify() && nickname.value!!.isNotEmpty() }
    }

    private fun isValidModify() =
        nickname.value != oldNickname.value || profileImgUri.value != oldProfileImgUri.value

    fun setUserInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                userUseCase.getUserInfo()
            }.onSuccess {
                nickname.postValue(it.nickname ?: "")
                oldNickname.postValue(it.nickname ?: "")
                profileImgUri.postValue(it.profile?.toUri())
                oldProfileImgUri.postValue(it.profile?.toUri())
            }.onFailure {
            }
        }
    }

    fun editUserInfo() {
        viewModelScope.launch {
            _isSuccess.postValue(
                Event(
                    editUserInfoUserCase.editUserInfo(
                        profileImageMultiPart,
                        requireNotNull(nickname.value)
                    )
                )
            )
        }
    }

    fun setNicknameClear() {
        nickname.value = ""
    }

    fun setProfileImgUri(uri: Uri, file: File) {
        profileImgUri.postValue(uri)
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        profileImageMultiPart = MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}