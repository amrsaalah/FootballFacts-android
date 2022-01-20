package com.football.facts.ui.utils.broadcast

import android.content.Intent
import com.football.facts.FootballApplication
import timber.log.Timber

class BroadcastManager {


    fun sendBroadcast(broadcast : String){
        val activity = FootballApplication.getCurrentActivity() ?: return
        val intent = Intent(broadcast)
        Timber.d("sendBroadcast: $intent")
        activity.sendBroadcast(intent)
    }
}