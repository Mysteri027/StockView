package ru.igorsh.stockview.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE


const val USER_SHARED_PREF_KEY = "USER_SHARED_PREF_KEY"
const val AUTH_STATUS = "AUTH_STATUS"
const val JWT_TOKEN = "JWT_TOKEN"

class SharedPrefUserStorage(
    context: Context
) : UserStorage {

    private val sharedPreferences = context.getSharedPreferences(USER_SHARED_PREF_KEY, MODE_PRIVATE)

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(AUTH_STATUS, false)
    }

    override fun set(status: Boolean) {
        sharedPreferences.edit().putBoolean(AUTH_STATUS, status).apply()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(JWT_TOKEN, token).apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(JWT_TOKEN, "") ?: ""
    }


}