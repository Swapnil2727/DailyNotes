package com.example.dailynotes.notesdisplay

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
//import com.example.dailynotes.database.NotesDatabase
//import kotlinx.android.synthetic.main.card_note.view.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.util.function.BinaryOperator

class NotesDisplayViewModel(val database: NotesDao, application: Application) : AndroidViewModel(application) {
    val allNotes = database.getAllNotes()

    private var _navigateToUpdateFragment = MutableLiveData<Notes?>()

    val navigateToUpdateFragment: MutableLiveData<Notes?> get() = _navigateToUpdateFragment

    val navigateToCreateFragment = MutableLiveData<Boolean>()

    init {
        navigateToCreateFragment.value = false
    }

    fun onAddClick()
    {
        navigateToCreateFragment.value = true
    }

    fun onNavigateToCreateComplete()
    {
        navigateToCreateFragment.value = false
    }

    fun onNavigationToUpdateComplete()
    {
        navigateToUpdateFragment.value = null
    }

    fun onNoteClicked(note: Notes)
    {

        _navigateToUpdateFragment.value = note
    }


}
