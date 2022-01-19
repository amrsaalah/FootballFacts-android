package com.football.facts.ui.utils.navigation

import androidx.navigation.NavDirections
import com.football.facts.FootballApplication
import com.football.facts.R
import com.football.facts.ui.utils.extensions.findNavControllerFromNavHost

class NavigationManager {

    fun navigate(direction: NavDirections) {
        val activity = FootballApplication.getCurrentActivity() ?: return
        val navController = activity.findNavControllerFromNavHost(R.id.navHostFragment)
        navController.navigate(direction)
    }


    fun finish(){
        val activity = FootballApplication.getCurrentActivity() ?: return
        activity.finish()
    }
}