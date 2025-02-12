package dev.training.eilaji_plus.app_system

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private enum class SharedPreferencesKeys {
        IS_FIRST_TIME, TOKEN, FULL_NAME, IMAGE_URL, CURRENT_USER_CHATTING_UID
    }

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()


    val currentUserChattingUID: String?
        get() = sharedPreferences.getString(
            SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name,
            ""
        )

    fun putCurrentUserChattingUID(uid: String?) {
        editor.putString(SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name, uid)
        editor.apply()
    }

    fun removeCurrentUserChattingUID() {
        editor.remove(SharedPreferencesKeys.CURRENT_USER_CHATTING_UID.name)
        editor.apply()
    }

    val imageUrl: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.IMAGE_URL.name, "default")

    fun putImageUrl(token: String?) {

        editor.putString(SharedPreferencesKeys.IMAGE_URL.name, token)
        editor.apply()
    }

    val fullName: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.FULL_NAME.name, null)

    fun putFullName(token: String?) {

        editor.putString(SharedPreferencesKeys.FULL_NAME.name, token)
        editor.apply()
    }

    val token: String?
        get() = sharedPreferences.getString(SharedPreferencesKeys.TOKEN.name, null)

    fun putToken(token: String?) {
        editor.putString(SharedPreferencesKeys.TOKEN.name, token)
        editor.apply()
    }

    val lastNotificationId: Int
        // FOR DATABASE NOTIFICATION ID CREATION
        get() = sharedPreferences.getInt("LAST_NOTIFICATION_ID", 0)

    fun putNewNotificationId(id: Int) {

        editor.putInt("LAST_NOTIFICATION_ID", id)
        editor.apply()
    }

    //------------------------------------
    var isFirstTime: Boolean
        get() {
            print("sharedPreferences$sharedPreferences")
            print(
                "sharedPreferences" + sharedPreferences.getBoolean(
                    SharedPreferencesKeys.IS_FIRST_TIME.name,
                    true
                )
            )
            return sharedPreferences.getBoolean(SharedPreferencesKeys.IS_FIRST_TIME.name, true)
        }
        set(isFirstTime) {
            editor.putBoolean(SharedPreferencesKeys.IS_FIRST_TIME.name, isFirstTime)
            editor.apply()
        }

    // =================================
    // when user logout for instance
    fun clear() {
        editor.clear().apply()
    }
}
