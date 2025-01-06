package ru.yatsevyukcompany.bloodpleasurediary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yatsevyukcompany.bloodpleasurediary.db.AppDatabase
import ru.yatsevyukcompany.bloodpleasurediary.db.Entry

class EditEntryActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var fieldUpEditText: EditText
    private lateinit var fieldDownEditText: EditText
    private lateinit var fieldPulseEditText: EditText
    private lateinit var fieldDateEditText: EditText
    private lateinit var fieldStatusEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button

    private var entryId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this)
        setContentView(R.layout.activity_edit_entry)

        fieldUpEditText = findViewById(R.id.fieldUpEditText)
        fieldDownEditText = findViewById(R.id.fieldDownEditText)
        fieldPulseEditText = findViewById(R.id.fieldPulseEditText)
        fieldDateEditText = findViewById(R.id.fieldDateEditText)
        fieldStatusEditText = findViewById(R.id.fieldStatusEditText)
        saveButton = findViewById(R.id.saveButton)
        deleteButton = findViewById(R.id.deleteButton)

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-bp")
            .fallbackToDestructiveMigration()
            .build()


        val fieldUp = intent.getStringExtra("fieldUp") ?: ""
        val fieldDown = intent.getStringExtra("fieldDown") ?: ""
        val fieldPulse = intent.getStringExtra("fieldPulse") ?: ""
        val fieldDate = intent.getStringExtra("fieldDate") ?: ""
        val fieldStatus = intent.getStringExtra("fieldStatus") ?: ""
        entryId = intent.getIntExtra("entryId", -1) //


        fieldUpEditText.setText(fieldUp)
        fieldDownEditText.setText(fieldDown)
        fieldPulseEditText.setText(fieldPulse)
        fieldDateEditText.setText(fieldDate)
        fieldStatusEditText.setText(fieldStatus)


        saveButton.setOnClickListener {
            val updatedEntry = Entry(
                id = entryId,
                fieldUp = fieldUpEditText.text.toString(),
                fieldDown = fieldDownEditText.text.toString(),
                fieldPusle = fieldPulseEditText.text.toString(),
                fieldStatus = fieldStatusEditText.text.toString(),
                timestamp = fieldDateEditText.text.toString()
            )
            CoroutineScope(Dispatchers.IO).launch {
                database.entryDao().updateItem(updatedEntry)
                runOnUiThread {
                    Toast.makeText(this@EditEntryActivity, "Запись обновлена", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }


        deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val entryToDelete = Entry(
                    id = entryId,
                    fieldUp = fieldUp,
                    fieldDown = fieldDown,
                    fieldPusle = fieldPulse,
                    fieldStatus = fieldStatus,
                    timestamp = fieldDate
                )
                database.entryDao().deleteItem(entryToDelete)
                runOnUiThread {
                    Toast.makeText(this@EditEntryActivity, "Запись удалена", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
