package dev.training.eilaji_plus.vms

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.training.eilaji_plus.app_system.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefManager: PreferencesManager
) : ViewModel() {

    fun sayHello() = "Hello from MainViewModel";
}