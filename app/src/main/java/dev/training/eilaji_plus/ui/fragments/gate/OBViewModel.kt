package dev.training.eilaji_plus.ui.fragments.gate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OBViewModel: ViewModel() {
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    var previousPage = 0

    fun setCurrentPage(page: Int) {
        _currentPage.value = page
    }

    fun reachedTheLastPage() {
        _navigateToLogin.value = true
    }
}