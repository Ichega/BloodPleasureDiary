package ru.yatsevyukcompany.bloodpleasurediary.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fieldUp: String,
    val fieldDown: String,
    val fieldPusle: String,
    val fieldStatus: String,
    val timestamp: String
)
