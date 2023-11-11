package com.faza.challenge_4

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {
    private lateinit var preference: SharedPreferences
    private const val sharedPreferenceName = "SharedPreferenceFoodShop"

    fun initial (context: Context) {
        preference = context.getSharedPreferences (sharedPreferenceName, Context.MODE_PRIVATE)
    }

    fun getPreference (key: String, value: Boolean) : Boolean {
        return preference.getBoolean(key, value)
    }

    fun setPreference (key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = preference.edit()
        with(editor) {
            putBoolean(key, value)
            apply()
            commit()
        }
    }
}
