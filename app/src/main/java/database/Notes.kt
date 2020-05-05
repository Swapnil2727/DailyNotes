package database

import android.provider.Settings
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo(name = "date")
    var creationDate: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "title")
    val noteTitle: String,
    @ColumnInfo(name = "note_detail")
    val noteDetail: String

)
