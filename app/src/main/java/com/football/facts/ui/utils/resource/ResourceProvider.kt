package com.football.facts.ui.utils.resource

import android.content.Context

class ResourceProvider(
    private val context: Context
) {

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getString(resId: Int, value: String): String {
        return context.getString(resId, value)
    }
}