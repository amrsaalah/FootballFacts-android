package com.football.facts.ui.utils.locale

import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import com.football.facts.ui.utils.locale.LocaleLanguages.ARABIC
import com.football.facts.ui.utils.locale.LocaleLanguages.DEFAULT_LANGUAGE
import com.football.facts.ui.utils.locale.LocaleLanguages.ENGLISH
import java.util.*

object LocaleHelper {

    private const val SHARED_PREF_NAME_LANGUAGE = "SHARED_PREF_NAME_LANGUAGE"
    private const val SHARED_PREF_LANGUAGE_VALUE = "SHARED_PREF_LANGUAGE_VALUE"

    fun onAttach(context: Context?): Context? {
        val language = getCurrentLanguage(context)
        return setLocale(context, language)
    }


    fun setLocale(context: Context?, language: String): Context? {
        saveLanguage(context, language)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            updateResources(context, language)
        } else {
            updateResourcesLegacy(context, language)
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun updateResources(context: Context?, language: String): Context? {
        if (context == null) return null
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = res.configuration
        val newLocale = Locale(language)

        config.setLocale(newLocale)
        val localeList = LocaleList(newLocale)
        LocaleList.setDefault(localeList)
        config?.setLocales(localeList)
        config?.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    @Suppress("DEPRECATION")
    private fun updateResourcesLegacy(context: Context?, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context?.resources

        val config = res?.configuration
        config?.setLocale(locale)
        config?.setLayoutDirection(locale)
        res?.updateConfiguration(config, res.displayMetrics)

        return context
    }

    fun getCurrentLanguage(context: Context?): String {
        var deviceLanguage = Locale.getDefault().language
        if (deviceLanguage != ENGLISH && deviceLanguage != ARABIC) {
            deviceLanguage = DEFAULT_LANGUAGE
        }
        if (context == null) return deviceLanguage
        val pref = context.getSharedPreferences(SHARED_PREF_NAME_LANGUAGE, Context.MODE_PRIVATE)
        return pref.getString(
            SHARED_PREF_LANGUAGE_VALUE, deviceLanguage
        ) ?: deviceLanguage
    }

    private fun saveLanguage(context: Context?, language: String) {
        val pref = context?.getSharedPreferences(SHARED_PREF_NAME_LANGUAGE, Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString(SHARED_PREF_LANGUAGE_VALUE, language)
        editor?.commit()
    }


    fun changeLanguage(context: Context?) {
        val currentLanguage = getCurrentLanguage(context)
        if (currentLanguage == ARABIC) {
            setLocale(context, ENGLISH)
        } else {
            setLocale(context, ARABIC)
        }
    }
}