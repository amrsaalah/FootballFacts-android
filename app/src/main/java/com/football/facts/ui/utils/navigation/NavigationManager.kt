package com.football.facts.ui.utils.navigation

import androidx.navigation.NavDirections
import com.football.facts.ComposeApplication
import com.football.facts.R
import com.football.facts.ui.utils.extensions.findNavControllerFromNavHost

class NavigationManager {

    fun navigate(direction: NavDirections) {
        val activity = ComposeApplication.getCurrentActivity() ?: return
        val navController = activity.findNavControllerFromNavHost(R.id.navHostFragment)
        navController.navigate(direction)
    }


    fun finish(){
        val activity = ComposeApplication.getCurrentActivity() ?: return
        activity.finish()
    }
}