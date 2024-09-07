package dev.training.eilaji_plus.vms

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: /*MainRepository*/ String,
) : ViewModel() {

    fun sayHello() = "Hello from MainViewModel";
}