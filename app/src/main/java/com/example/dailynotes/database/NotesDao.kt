package com.example.dailynotes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NotesDao {

    @Insert
    fun insert(notes: Notes): Long

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Notes>>

}


