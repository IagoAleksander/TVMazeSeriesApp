package com.iaz.tvmazeseriesapp.repository

import android.content.Context
import android.content.SharedPreferences
import com.iaz.tvmazeseriesapp.util.SharedPreferenceStringSetLiveData

class Prefs(context: Context) {
    companion object {
        const val APP_PREFS = "prefs"
        const val FAVORITES = "favorites"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)

    var sharedPreferenceStringLiveData = SharedPreferenceStringSetLiveData(preferences, FAVORITES, mutableSetOf())

    var favoritesPref: Set<String>
        get() = sharedPreferenceStringLiveData.value ?: setOf()
        set(value) = preferences.edit().putStringSet(FAVORITES, value).apply()
}