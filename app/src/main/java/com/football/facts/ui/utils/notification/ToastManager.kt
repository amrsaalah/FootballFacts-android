package com.football.facts.ui.utils.notification

import android.widget.Toast
import com.football.facts.ComposeApplication

class ToastManager {

    fun showMessage(message : String?){
        if(!message.isNullOrEmpty()){
            val activity = ComposeApplication.getCurrentActivity() ?: return
            Toast.makeText(activity , message , Toast.LENGTH_LONG).show()
        }
    }

}