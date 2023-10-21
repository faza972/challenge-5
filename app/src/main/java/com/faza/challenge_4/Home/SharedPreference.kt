package com.faza.challenge_4.Home

import android.content.Context
import androidx.core.content.edit

class SharedPreference(context: Context) {
    private val sharedPreferenceName = "SharedPreferenceFoodShop"
    private val preference = context.getSharedPreferences(sharedPreferenceName, 0)

    var isGrid: Boolean
        get() = preference.getBoolean("IS_GRID", false)
        set(value) {
            preference.edit().putBoolean("IS_GRID", value).apply()
        }
}
