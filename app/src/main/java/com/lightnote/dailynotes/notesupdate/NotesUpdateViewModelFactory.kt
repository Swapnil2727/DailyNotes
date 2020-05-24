package com.lightnote.dailynotes.notesupdate

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lightnote.dailynotes.database.Notes
import com.lightnote.dailynotes.database.NotesDao

class NotesUpdateViewModelFactory(
    private val dataSource: NotesDao,
    private val application: Application,
    private val notes:Notes

    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesUpdateViewModel::class.java)) {
            return NotesUpdateViewModel(dataSource, application, notes) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}