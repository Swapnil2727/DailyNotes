package com.example.dailynotes.notecreate

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailynotes.database.NotesDao


class NotesCreateViewModelFactory(
    private val dataSource: NotesDao,
    private val application: Application

) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesCreateViewModel::class.java)) {
            return NotesCreateViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}