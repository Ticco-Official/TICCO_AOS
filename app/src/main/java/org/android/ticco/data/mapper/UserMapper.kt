package org.android.ticco.data.mapper

import org.android.ticco.data.datasource.remote.BasicResponse
import org.android.ticco.data.datasource.remote.user.model.UserResponse
import org.android.ticco.domain.model.User

fun UserResponse.toEntity():User = User(nickname = data.nickname, profile = data.profileImageUrl)

fun BasicResponse.getSuccess():Boolean = success