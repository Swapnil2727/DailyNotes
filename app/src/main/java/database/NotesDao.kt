package database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface NotesDao {

    @Insert
    fun insert(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Notes>>

}


