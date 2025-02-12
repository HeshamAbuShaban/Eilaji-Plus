package dev.training.eilaji_plus.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.app_system.PreferencesManager
import dev.training.eilaji_plus.databinding.ActivityEntranceBinding
import dev.training.eilaji_plus.vms.EntranceViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EntranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEntranceBinding
    private val viewModel: EntranceViewModel by viewModels()
    private lateinit var navController: NavController
    @Inject
    lateinit var pref: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_entrance) as NavHostFragment
        navController = navHostFragment.navController

        checkFirstTime()
        observeViewModel()
    }

    private fun checkFirstTime() {
        viewModel.checkFirstTime(
            pref.isFirstTime,
            pref.token
        )
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    handleScreenNavigation(state.currentScreen)
                }
            }
        }
    }

    private fun handleScreenNavigation(screen: EntranceViewModel.Screen) {
        when (screen) {
            EntranceViewModel.Screen.OnBoarding -> navigateTo(R.id.destination_onBoarding)
            EntranceViewModel.Screen.Login -> navigateTo(R.id.destination_login)
            EntranceViewModel.Screen.SignUp -> navigateTo(R.id.destination_register)
            EntranceViewModel.Screen.ForgetPassword -> navigateTo(R.id.destination_forgot_password)
            EntranceViewModel.Screen.Home -> navigateToHome()
        }
    }

    private fun navigateTo(destinationId: Int) {
        navController.navigate(destinationId)
    }

    private fun navigateToHome() {
        startActivity(Intent(this@EntranceActivity, MainActivity::class.java))
        finish()
    }
}
