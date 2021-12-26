package com.football.facts

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.football.facts.ui.base.BaseActivity
import com.football.facts.ui.utils.lifecycle.ComposeActivityLifecycleCallbacks
import com.football.facts.ui.utils.locale.LocaleHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class ComposeApplication : Application() {
    val activityLifecycleCallbacks = ComposeActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        private var instance: ComposeApplication? = null

        fun getCurrentActivity(): BaseActivity? {
            val activity = instance?.activityLifecycleCallbacks?.currentActivity
            return if (activity != null && activity is BaseActivity) activity else null
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleHelper.setLocale(this, LocaleHelper.getCurrentLanguage(this))
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }
}