package ru.yatsevyukcompany.bloodpleasurediary

import androidx.appcompat.app.AppCompatActivity

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val THEME_KEY = "app_theme"

    const val THEME_BLUE = "blue"
    const val THEME_GREEN = "green"
    const val THEME_ORANGE = "orange"

    fun applyTheme(theme: String, activity: AppCompatActivity) {
        val sharedPreferences = activity.getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().putString(THEME_KEY, theme).apply()
        activity.recreate()
    }

    fun setTheme(activity: AppCompatActivity) {
        val sharedPreferences = activity.getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val theme = sharedPreferences.getString(THEME_KEY, THEME_BLUE)
        when (theme) {
            THEME_BLUE -> activity.setTheme(R.style.Theme_BloodPleasureDiary_Blue)
            THEME_GREEN -> activity.setTheme(R.style.Theme_BloodPleasureDiary_Green)
            THEME_ORANGE -> activity.setTheme(R.style.Theme_BloodPleasureDiary_Orange)
        }

    }
}
