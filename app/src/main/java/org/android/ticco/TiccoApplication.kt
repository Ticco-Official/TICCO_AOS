package org.android.ticco

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import org.android.ticco.data.datasource.local.SharedPreferences

@HiltAndroidApp
class TiccoApplication: Application() {

    companion object {
        lateinit var preferences: SharedPreferences
    }
    override fun onCreate() {
        super.onCreate()
        preferences = SharedPreferences(applicationContext)
        KakaoSdk.init(this, getString(R.string.kakao_native_app_key))
    }
}