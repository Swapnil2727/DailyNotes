package com.example.dailynotes.notecreate

import android.app.Application
import androidx.lifecycle.*
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesCreateViewModel(val database: NotesDao, application: Application) :
    AndroidViewModel(application) {

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

    fun onAdded(note: Notes) {
        viewModelScope.launch {
            insert(note)
            _navigateToNotesDisplay.value = true
        }


    }

    fun onNavigateToNotesDisplayComplete() {
        _navigateToNotesDisplay.value = false
    }

}
