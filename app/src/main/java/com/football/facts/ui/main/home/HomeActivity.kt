package com.football.facts.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.football.facts.R
import com.football.facts.databinding.ActivityHomeBinding
import com.football.facts.ui.base.BaseActivity
import com.football.facts.ui.utils.extensions.findNavControllerFromNavHost
import com.football.facts.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    override val viewModel by viewModels<HomeViewModel>()
    override val binding by viewBinding(ActivityHomeBinding::inflate)

    private val navController by lazy {
        findNavControllerFromNavHost(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // there is a bug in splashscreen API library with Xiaomi devices in dark mode , hopefully it is fixed soon.  https://stackoverflow.com/questions/70619915/splash-screen-api-not-support-in-dark-mode
        installSplashScreen()
        super.onCreate(savedInstanceState)

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        navController.addOnDestinationChangedListener(navigationDestinationListener)


        checkApiStatus()
    }



    private fun checkApiStatus(){
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                val isReady = viewModel.isReady.value
                if (isReady) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                }
                return isReady
            }
        })
    }


    private val navigationDestinationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.countriesFragment ||
                destination.id == R.id.searchTeamsFragment ||
                destination.id == R.id.favoritesFragment){
                binding.bottomNavigation.visibility = View.VISIBLE
            }else{
                binding.bottomNavigation.visibility = View.GONE
            }
        }


    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(navigationDestinationListener)
        super.onDestroy()
    }
}