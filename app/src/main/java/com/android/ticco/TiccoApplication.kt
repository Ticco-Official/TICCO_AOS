package com.android.ticco

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TiccoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}