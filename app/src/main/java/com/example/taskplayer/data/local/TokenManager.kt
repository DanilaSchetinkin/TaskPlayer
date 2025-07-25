package com.example.taskplayer.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.taskplayer.screen.PhotoScreen.GalleryItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TokenManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_NICK_NAME = "nick_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_AVATAR = "avatar"
        private const val KEY_LAST_EMAIL = "last_email"
        private const val KEY_GALLERY = "user_gallery"
        private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    }

    fun saveAuthData(
        token: String,
        userId: String,
        email: String,
        nickName: String,
        avatar: String?
    ) {
        sharedPreferences.edit {
            putString(KEY_TOKEN, token)
            putString(KEY_USER_ID, userId)
            putString(KEY_USER_EMAIL, email)
            putString(KEY_NICK_NAME, nickName)
            putString(KEY_AVATAR, avatar)
            putString(KEY_LAST_EMAIL, email)
        }
    }

    fun isLoggedIn(): Boolean = !getToken().isNullOrEmpty()

    fun getToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)
    fun getUserId(): String = sharedPreferences.getString(KEY_USER_ID, "") ?: ""
    fun getNickName(): String = sharedPreferences.getString(KEY_NICK_NAME, "") ?: ""
    fun getAvatar(): String? = sharedPreferences.getString(KEY_AVATAR, null)
    fun getEmail(): String = sharedPreferences.getString(KEY_USER_EMAIL, "") ?: ""
    fun getLastEmail(): String? = sharedPreferences.getString(KEY_LAST_EMAIL, null)


    fun saveGallery(gallery: List<GalleryItem>) {
        val jsonString = gson.toJson(gallery)
        sharedPreferences.edit {
            putString(KEY_GALLERY, jsonString)
        }
    }

    fun getGallery(): List<GalleryItem> {
        val jsonString = sharedPreferences.getString(KEY_GALLERY, null) ?: return emptyList()
        val type = object : TypeToken<List<GalleryItem>>() {}.type
        return gson.fromJson(jsonString, type) ?: emptyList()
    }


    fun clearAuthData() {
        sharedPreferences.edit {
            remove(KEY_TOKEN)
            remove(KEY_USER_ID)
            remove(KEY_NICK_NAME)
            remove(KEY_AVATAR)
        }
    }

    fun isOnboardingShown(): Boolean {
        return sharedPreferences.getBoolean(KEY_ONBOARDING_SHOWN, false)
    }

    fun setOnboardingShown() {
        sharedPreferences.edit {
            putBoolean(KEY_ONBOARDING_SHOWN, true)
        }
    }

//    Временная
    fun resetAllData() {
        sharedPreferences.edit {
            clear()
        }
    }


}