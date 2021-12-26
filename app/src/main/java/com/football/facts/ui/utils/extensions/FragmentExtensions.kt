package com.football.facts.ui.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow


inline fun <T> Fragment.collectWithLifecycle(
    flow: Flow<T>,
    crossinline action: (T) -> Unit
) {
    val activity = activity
    if (activity is AppCompatActivity) {
        activity.collectWithLifecycle(flow, action)
    }
}