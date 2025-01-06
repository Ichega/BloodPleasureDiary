package ru.yatsevyukcompany.bloodpleasurediary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        ThemeManager.setTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)



        findViewById<View>(R.id.buttonBlue).setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.THEME_BLUE, this)
            Toast.makeText(this@SettingsActivity, "Установлена голубая тема", Toast.LENGTH_SHORT).show()

        }

        findViewById<View>(R.id.buttonGreen).setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.THEME_GREEN, this)
            Toast.makeText(this@SettingsActivity, "Установлена зеленая тема", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.buttonOrange).setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.THEME_ORANGE, this)
            Toast.makeText(this@SettingsActivity, "Установлена оранжевая тема", Toast.LENGTH_SHORT).show()
        }
    }
}
