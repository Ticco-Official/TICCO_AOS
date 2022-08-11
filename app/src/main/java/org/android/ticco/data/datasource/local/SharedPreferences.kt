package org.android.ticco.data.datasource.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var accessToken: String?
        get() = prefs.getString("accessToken", null)
        set(value) = prefs.edit().putString("accessToken", value).apply()

    var refreshToken: String?
        get() = prefs.getString("refreshToken", null)
        set(value) = prefs.edit().putString("refreshToken", value).apply()

    fun clearPreferences(){
        prefs.edit().clear().apply()
    }
}