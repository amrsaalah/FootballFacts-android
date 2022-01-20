package com.football.facts.ui.base

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment(resId : Int) : Fragment(resId) {
    abstract val viewModel: BaseViewModel
    abstract val binding: ViewBinding

    open var broadcastReceivers: List<Pair<String, BroadcastReceiver>>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        broadcastReceivers?.forEach { broadcast ->
            val filter = IntentFilter(broadcast.first)
            activity?.registerReceiver(broadcast.second, filter)
        }
    }


    override fun onDestroyView() {
        broadcastReceivers?.forEach { broadcast ->
            activity?.unregisterReceiver(broadcast.second)
        }
        super.onDestroyView()
    }
}