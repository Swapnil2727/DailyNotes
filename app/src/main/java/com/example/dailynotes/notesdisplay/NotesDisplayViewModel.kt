package com.example.dailynotes.notesdisplay

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import database.Notes
import database.NotesDao
import database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.function.BinaryOperator

class NotesDisplayViewModel(val database: NotesDao, application: Application) : AndroidViewModel(application) {
    val allNotes = database.getAllNotes()

//    private suspend fun insert(notes: Notes)
//    {
//        withContext(Dispatchers.IO)
//        {
//            database.insert(notes)
//        }
//    }

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

}
