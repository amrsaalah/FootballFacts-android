package com.football.facts.ui.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


fun AppCompatActivity.findNavControllerFromNavHost(navHostId: Int): NavController {
    val navHostFragment = this.supportFragmentManager.findFragmentById(navHostId) as NavHostFragment
    return navHostFragment.navController
}



inline fun <T> AppCompatActivity.collectWithLifecycle(
    flow: Flow<T>,
    crossinline action: (T) -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                flow.collect {
                    action.invoke(it)
                }
            }
        }
    }
}