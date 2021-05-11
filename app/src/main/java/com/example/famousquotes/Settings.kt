package com.bizmiz.alishernavoiy

import android.content.Context

class Settings(context: Context) {
    companion object {
        const val DARK_MODE = "darkMode"
        const val TEXT_SIZE = "TextSize"
    }

    private val prefs =
        context.getSharedPreferences("${context.packageName}.settings", Context.MODE_PRIVATE)

    fun changeAppMode() {
        if (isAppDarkMode()) {
            prefs.edit().putBoolean(DARK_MODE, false).apply()
        } else {
            prefs.edit().putBoolean(DARK_MODE, true).apply()
        }
    }

    fun isAppDarkMode(): Boolean = prefs.getBoolean(DARK_MODE, false)

    fun decrementTextSize() {
        prefs.edit().putFloat(TEXT_SIZE, getTextSize() - 2).apply()
    }

    fun incrementTextSize() {
        prefs.edit().putFloat(TEXT_SIZE, getTextSize() + 2).apply()
    }

    fun getTextSize(): Float {
        return prefs.getFloat(TEXT_SIZE, 18f)
    }
}