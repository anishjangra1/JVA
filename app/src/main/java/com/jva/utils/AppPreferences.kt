package com.jva.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

// todo: make it abstracted
@Singleton
class AppPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
){


    fun  saveUserDetails(
        userId: String,
        userRole: String,
        token: String,
    ){
        sharedPreferences.edit().apply {
            putInt(USER_ID, userId.toInt())
            putString(USER_ROLE, userRole)
            putString(TOKEN, token)
        }.apply()
    }

    fun saveBoolean(key: String, value: Boolean){
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun saveUserType(userType: String){
        sharedPreferences.edit().putString(USER_TYPE, userType).apply()
    }
    fun saveUserData(userKey: String,userValue : String){
        sharedPreferences.edit().putString(userKey, userValue).apply()
    }

    fun getUserType() = sharedPreferences.getString(USER_TYPE, "")

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun getToken() = sharedPreferences.getString(TOKEN, "")

    fun getUserId() = sharedPreferences.getInt(USER_ID, 0).toString()

    fun getUserRole() = sharedPreferences.getString(USER_ROLE, "")
    fun getUserName()   = sharedPreferences.getString(USER_NAME, "")


    companion object{
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_ROLE = "user_role"
        const val TOKEN = "token"
        const val USER_TYPE = "user_type"
        private const val PROFILE = "profile"
    }
}