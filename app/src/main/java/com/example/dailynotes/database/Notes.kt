package com.example.dailynotes.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,

    @ColumnInfo(name = "title")
    var noteTitle: String,

    @ColumnInfo(name = "note_detail")
    var noteDetail: String

)
