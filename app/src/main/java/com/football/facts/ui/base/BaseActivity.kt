package com.football.facts.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.football.facts.ui.utils.locale.LocaleHelper
import com.football.facts.ui.utils.locale.LocaleLanguages.ENGLISH

abstract class BaseActivity : AppCompatActivity() {

    abstract val viewModel : BaseViewModel
    abstract val binding : ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (LocaleHelper.getCurrentLanguage(this) == ENGLISH) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}