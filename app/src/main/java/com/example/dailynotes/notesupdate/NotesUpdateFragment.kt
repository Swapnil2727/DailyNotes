package com.example.dailynotes.notesupdate

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.dailynotes.R
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
import com.example.dailynotes.database.NotesDatabase
import com.example.dailynotes.databinding.NotesUpdateFragmentBinding
import com.example.dailynotes.hideKeyboard

class NotesUpdateFragment : Fragment() {


    private lateinit var notesUpdateViewModel: NotesUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get reference to binding and infalte fragment
        val binding: NotesUpdateFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.notes_update_fragment,container,false)

        val application: Application = requireNotNull(this.activity).application
        val datasource: NotesDao = NotesDatabase.getInstance(application).notesDao
        val arguments = NotesUpdateFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = NotesUpdateViewModelFactory(datasource,application,arguments.note)

        notesUpdateViewModel = ViewModelProvider(this,viewModelFactory).get(NotesUpdateViewModel::class.java)

        binding.lifecycleOwner = this

        binding.notesUpdateViewModel = notesUpdateViewModel



        binding.titleUpdateText.setText(arguments.note.noteTitle)
        binding.notesUpdateText.setText(arguments.note.noteDetail)
        binding.floatingUpdateButton.setOnClickListener(View.OnClickListener {

            var title = binding.titleUpdateText.text.toString()
            var detail = binding.notesUpdateText.text.toString()
            var note =Notes(noteTitle = title,noteDetail = detail)
            note.noteId = arguments.note.noteId

            //Upsert Insert onconflict Resolve
            notesUpdateViewModel.onAdded(note)
            //hidekeyboard
            hideKeyboard(requireActivity(),requireView())
        })

        binding.floatingDeleteButton.setOnClickListener(View.OnClickListener {
            notesUpdateViewModel.onDelete(arguments.note)
            hideKeyboard(requireActivity(),requireView())
        })

        notesUpdateViewModel.navigateToNotesDisplay.observe(viewLifecycleOwner, Observer {
            if(it)
            {
                findNavController().navigate(NotesUpdateFragmentDirections.actionNotesUpdateFragmentToNotesDisplayFragment())
                notesUpdateViewModel.onNavigateToNotesDisplayComplete()
            }
        })

        return binding.root
    }


}
