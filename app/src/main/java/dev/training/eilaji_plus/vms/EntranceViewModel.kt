package dev.training.eilaji_plus.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.training.eilaji_plus.core.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntranceViewModel @Inject constructor(
    private val pref: PreferencesManager
) : ViewModel() {

    enum class Screen {
        OnBoarding, Login, SignUp, ForgetPassword, Home
    }

    data class EntranceState(
        val currentScreen: Screen = Screen.OnBoarding,
    )

    private val _state = MutableStateFlow(EntranceState())
    val state = _state.asStateFlow()

    /**
     * Check if it is the first time to open the app
     * @param isFirstTime Boolean
     */
    fun checkFirstTime(isFirstTime: Boolean, token: String?) {
        if (isFirstTime) {
            toOnBoarding()
            pref.isFirstTime = false
        } else {
            isLoggedIn(token ?: "")
        }
    }

    private fun isLoggedIn(token: String) {
        if (token.isNotBlank()) {
            toHome()
        } else {
            toLogin()
        }
    }

    /**
     * Moving to another screen
     * @param screen Screen
     */
    private fun moveToScreen(screen: Screen) {
        viewModelScope.launch {
            _state.emit(
                EntranceState(
                    currentScreen = screen,
                )
            )
        }
    }

    /**
     * Move to on-boarding screen
     */
    private fun toOnBoarding() {
        moveToScreen(Screen.OnBoarding)
    }

    /**
     * Move to login screen
     */
    fun toLogin() {
        moveToScreen(Screen.Login)
    }

    /**
     * Move to home screen
     */
    fun toHome() {
        moveToScreen(Screen.Home)
    }

    /**
     * Move to sign up screen
     */
    fun toSignUp() {
        moveToScreen(Screen.SignUp)
    }

    /**
     * Move to forget password screen
     */
    fun toForgetPassword() {
        moveToScreen(Screen.ForgetPassword)
    }



}
