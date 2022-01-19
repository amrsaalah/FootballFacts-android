package com.football.facts.ui.utils.notification

import android.widget.Toast
import com.football.facts.FootballApplication

class ToastManager {

    fun showMessage(message : String?){
        if(!message.isNullOrEmpty()){
            val activity = FootballApplication.getCurrentActivity() ?: return
            Toast.makeText(activity , message , Toast.LENGTH_LONG).show()
        }
    }

}