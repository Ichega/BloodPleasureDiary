package ru.yatsevyukcompany.bloodpleasurediary.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.yatsevyukcompany.bloodpleasurediary.db.Entry

@Dao
interface EntryDao {

    @Query("SELECT * FROM entries")
    suspend fun getAllEntries(): List<Entry>

    @Update
    suspend fun updateItem(item: Entry)

    @Delete
    suspend fun deleteItem(item: Entry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Entry)
}
