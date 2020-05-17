package com.example.dailynotes.notecreate

import android.app.Application
import android.content.Context
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
import com.example.dailynotes.databinding.NotesCreateFragmentBinding
import com.example.dailynotes.database.Notes
import com.example.dailynotes.database.NotesDao
import com.example.dailynotes.database.NotesDatabase

/**
 * A simple [Fragment] subclass.
 */
class NotesCreateFragment : Fragment() {

    private lateinit var notesCreateViewModel: NotesCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NotesCreateFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.notes_create_fragment,container,false)

        val application: Application = requireNotNull(this.activity).application
        val datasource: NotesDao = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = NotesCreateViewModelFactory(datasource,application)

        notesCreateViewModel = ViewModelProvider(this,viewModelFactory).get(NotesCreateViewModel::class.java)

        binding.lifecycleOwner = this

        binding.notesCreateViewModel = notesCreateViewModel

        binding.floatingDoneButton.setOnClickListener(View.OnClickListener {
            var title: String = binding.titleEditText.text.toString()
            var detail: String = binding.notesEditText.text.toString()

            if (title.isEmpty())
            {
                binding.titleTextInputLayout.error = getString(R.string.requireField)
                return@OnClickListener
            }
            //Don't need to add id, it is autoGenerated
            var note = Notes(noteTitle = title,noteDetail = detail)
            notesCreateViewModel.onAdded(note)
            hideKeyboard()
        })


        notesCreateViewModel.navigateToNotesDisplay.observe(viewLifecycleOwner, Observer {
            if(it)
            {
                findNavController().navigate(R.id.action_notesCreateFragment_to_notesDisplayFragment)
                notesCreateViewModel.onNavigateToNotesDisplayComplete()
            }
        })

        return  binding.root


    }

    private fun hideKeyboard() {
        // Hide the keyboard.
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


}
