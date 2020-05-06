package com.example.dailynotes.notesdisplay

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
import com.example.dailynotes.database.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.function.BinaryOperator

class NotesDisplayViewModel(val database: NotesDao, application: Application) : AndroidViewModel(application) {
    val allNotes = database.getAllNotes()



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
