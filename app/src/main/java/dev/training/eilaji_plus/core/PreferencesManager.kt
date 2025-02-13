package dev.training.eilaji_plus.core

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private enum class SharedPreferencesKeys {
        IS_FIRST_TIME, TOKEN, FULL_NAME, IMAGE_URL, CURRENT_USER_CHATTING_UID
    }

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()


    var currentUserChattingUID: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name, "")
        set(uid) = editor.putString(SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name, uid).apply()

    fun removeCurrentUserChattingUID() =
        editor.remove(SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name).apply()

    var imageUrl: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.IMAGE_URL.name, "default")
        set(value) = editor.putString(SharedPreferencesKeys.IMAGE_URL.name, value).apply()

    var fullName: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.FULL_NAME.name, null)
        set(fullName) = editor.putString(SharedPreferencesKeys.FULL_NAME.name, fullName).apply()

    var token: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.TOKEN.name, null)
        set(token) = editor.putString(SharedPreferencesKeys.TOKEN.name, token).apply()

    var lastNotificationId: Int
        get() = sharedPreferences.getInt("LAST_NOTIFICATION_ID", 0)
        set(id) = editor.putInt("LAST_NOTIFICATION_ID", id).apply()

    var isFirstTime: Boolean
        get() = sharedPreferences.getBoolean(SharedPreferencesKeys.IS_FIRST_TIME.name, true)
        set(isFirstTime) = editor.putBoolean(SharedPreferencesKeys.IS_FIRST_TIME.name, isFirstTime).apply()

    fun clear() = editor.clear().apply()
}
