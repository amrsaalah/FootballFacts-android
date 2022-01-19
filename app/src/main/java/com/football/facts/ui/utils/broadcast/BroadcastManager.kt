package com.football.facts.ui.utils.broadcast

import android.content.Intent
import com.football.facts.FootballApplication

class BroadcastManager {


    fun sendBroadcast(broadcast : String){
        val activity = FootballApplication.getCurrentActivity() ?: return
        val intent = Intent(broadcast)
        activity.sendBroadcast(intent)
    }
}