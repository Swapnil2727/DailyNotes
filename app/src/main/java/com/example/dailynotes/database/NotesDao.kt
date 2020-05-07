package com.example.dailynotes.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: Notes): Long

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table  ")
    fun clear()

    @Delete
    fun delete(note:Notes)

}


