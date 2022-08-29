package org.android.ticco.data.mapper

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import org.android.ticco.domain.model.User

fun UserResponse.Data.toEntity():User = User(nickname = this.nickname.toString(), profile = this.profileImageUrl.toString())

fun BasicResponse.getSuccess():Boolean = success