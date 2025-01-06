package ru.yatsevyukcompany.bloodpleasurediary
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yatsevyukcompany.bloodpleasurediary.db.AppDatabase
import ru.yatsevyukcompany.bloodpleasurediary.db.Entry
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class AddActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this)
        setContentView(R.layout.activity_add)

        val editUp = findViewById<EditText>(R.id.editUp)
        val editDown = findViewById<EditText>(R.id.editDown)
        val editPulse = findViewById<EditText>(R.id.editPulse)
        val editStatus = findViewById<EditText>(R.id.editStatus)
        val saveBtn = findViewById<Button>(R.id.buttonSave)

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-bp")
            .fallbackToDestructiveMigration()
            .build()

        saveBtn.setOnClickListener {
            val fieldUp = editUp.text.toString()
            val fieldDown = editDown.text.toString()
            val fieldPusle = editPulse.text.toString()
            val fieldStatus = editStatus.text.toString()


            if (fieldUp.isNotBlank() && fieldDown.isNotBlank()&& fieldPusle.isNotBlank()&& fieldStatus.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val currentDateTime = LocalDateTime.now()
                    val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("d. MMM yyyy HH:mm"))



                    val entry = Entry(
                        fieldUp = fieldUp,
                        fieldDown = fieldDown,
                        fieldPusle = fieldPusle,
                        fieldStatus = fieldStatus,
                        timestamp = formattedDateTime
                    )
                    database.entryDao().insertItem(entry)
                    launch(Dispatchers.Main) {
                        editUp.text.clear()
                        editDown.text.clear()
                        editPulse.text.clear()
                        editStatus.text.clear()
                        setResult(RESULT_OK)
                        finish()

                    }
                }
            }
        }
    }


}
