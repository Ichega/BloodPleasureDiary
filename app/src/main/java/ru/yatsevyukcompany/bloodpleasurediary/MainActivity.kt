package ru.yatsevyukcompany.bloodpleasurediary

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yatsevyukcompany.bloodpleasurediary.db.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.setTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabaddscrn = findViewById<FloatingActionButton>(R.id.fabAdd)


        applyCustomColors(fabaddscrn)
        fabaddscrn.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


        recyclerView = findViewById(R.id.recyclerView)
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-bp")
            .fallbackToDestructiveMigration()
            .build()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        updateEntries()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private val addActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {

                updateEntries()
            }
        }

    override fun onResume() {
        ThemeManager.setTheme(this)
        super.onResume()
        val fabaddscrn = findViewById<FloatingActionButton>(R.id.fabAdd)
        applyCustomColors(fabaddscrn)

        updateEntries()
    }

    private fun updateEntries() {
        CoroutineScope(Dispatchers.IO).launch {
            val entries = database.entryDao().getAllEntries()
            launch(Dispatchers.Main) {

                val adapter = EntryAdapter(entries)


                val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
                val theme = sharedPreferences.getString("app_theme", ThemeManager.THEME_BLUE)


                adapter.applyTheme(theme ?: ThemeManager.THEME_BLUE)


                recyclerView.adapter = adapter
            }
        }
    }


    private fun applyCustomColors(fabaddscrn: FloatingActionButton) {
        val sharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val theme = sharedPreferences.getString("app_theme", ThemeManager.THEME_BLUE)

        when (theme) {

            ThemeManager.THEME_BLUE -> {
                fabaddscrn.setBackgroundTintList(getColorStateList(R.color.blue))
                window.statusBarColor = getColor(R.color.blue)

            }
            ThemeManager.THEME_GREEN -> {
                fabaddscrn.setBackgroundTintList(getColorStateList(R.color.green))
                window.statusBarColor = getColor(R.color.green)

            }
            ThemeManager.THEME_ORANGE -> {
                fabaddscrn.setBackgroundTintList(getColorStateList(R.color.orange))
                window.statusBarColor = getColor(R.color.orange)

            }
        }
    }

}
