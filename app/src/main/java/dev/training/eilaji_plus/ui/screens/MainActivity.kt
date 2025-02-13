package dev.training.eilaji_plus.ui.screens

import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Menu
import android.view.MenuItem
import android.transition.Fade
import android.transition.TransitionSet
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.databinding.ActivityMainBinding
import dev.training.eilaji_plus.utils.AlertUtils.showToast

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /*private val viewModel: MainViewModel by viewModels()*/
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.includeAppBarLayoutBase.toolbarApp)
        init()
    }

    private fun init() {
        setupViewsCon()
        setupListener()
    }

    private fun setupViewsCon() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        hideBottomNavViewAtStart()
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController()
    }

    private fun setupActionBarWithNavController() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.destination_home,
                R.id.destination_chat,
                R.id.destination_prescription,
                R.id.destination_categories,
                R.id.destination_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun hideBottomNavViewAtStart() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.destination_search,
                R.id.destination_reminder,
                R.id.destination_map -> binding.navView.visibility = android.view.View.GONE

                else -> binding.navView.visibility = android.view.View.VISIBLE
            }
        }
    }

    private fun setupListener() {
        with(binding) {
            includeAppBarLayoutBase.toolbarApp.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    // Handle ToolBar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    // ************ ~these are the menu builder methods~ ************
    private fun updateToolbarMenu() {
        val toolbar = binding.includeAppBarLayoutBase.toolbarApp
        // Update toolbar menu based on the selected bottom navigation item
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val menuResource = when (destination.id) {
                R.id.destination_home -> R.menu.home_menu
                R.id.destination_categories -> R.menu.category_menu
                // Add more destinations and their associated menu resources here
                else -> 0
            }
            toolbar.menu.clear()
            if (menuResource != 0) {
                toolbar.inflateMenu(menuResource)
            }
        }

        // Handle bottom navigation item selection
        binding.navView.setOnItemSelectedListener { menuItem ->
            val handled =
                NavigationUI.onNavDestinationSelected(menuItem, navController)
            if (handled) {
                invalidateOptionsMenu()
            }
            handled
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.empty_menu, menu) // Inflate an empty menu
        updateToolbarMenu() // Update the toolbar menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        // Category Search Button
        R.id.search_menu_item -> {
            showToast(this, "search_menu")
             with(window) {
                enterTransition = Explode().apply { duration = 500 } // Adjust the duration as needed
                exitTransition = Fade().apply { duration = 500 } // Adjust the duration as needed
             }
            navController.navigate(R.id.destination_search, null, navOptions())
            true
        }
        // Home Search Button
        R.id.mi_search -> {
            showToast(this, "search_menu")
            with(window) {
                enterTransition = Explode().apply { duration = 500 }
                exitTransition = Fade().apply { duration = 500 }
             }
             navController.navigate(R.id.destination_search, null, navOptions())
            true
        }

        R.id.mi_notification -> {
            showToast(this, "notification_menu")
            navController.navigate(R.id.destination_reminder, null, navOptions())
            true
        }

        R.id.mi_pharmacies_map -> {
            showToast(this, "pharmacies_map_menu")
            navController.navigate(R.id.destination_map, null, navOptions())
            true
        }

        R.id.share_menu_item -> {
            showToast(this, "share_menu_item")
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Check out this app!")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "I found this amazing app that I wanted to share with you. Download it from [app store link]."
                )
            }

            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(shareIntent, "Share the app"))
            } else {
                showToast(this, "No app found to share")
            }
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun navOptions(): NavOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .build()

}