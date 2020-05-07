package com.example.dailynotes.notesupdate

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.*
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesUpdateViewModel(val database: NotesDao, application: Application, notes:Notes) :
    AndroidViewModel(application){

    private val _navigateToNotesDisplay = MutableLiveData<Boolean>()
    val navigateToNotesDisplay: LiveData<Boolean> get() = _navigateToNotesDisplay

    init {
        _navigateToNotesDisplay.value = false
    }

    private suspend fun insert(note: Notes) {
        withContext(Dispatchers.IO)
        {
            database.insert(note)
        }
    }

    private suspend fun delete(note:Notes)
    {
        withContext(Dispatchers.IO)
        {
            database.delete(note)
        }
    }

    fun onAdded(note: Notes) {
        viewModelScope.launch {
            insert(note)
            _navigateToNotesDisplay.value = true

        }
    }

    fun onDelete(note:Notes)
    {
        viewModelScope.launch {
            delete(note)
            _navigateToNotesDisplay.value = true
        }
    }
    fun onNavigateToNotesDisplayComplete() {
        _navigateToNotesDisplay.value = false
    }

}
