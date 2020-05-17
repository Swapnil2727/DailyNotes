package com.example.dailynotes.notesdisplay


import android.app.Application
import android.opengl.Visibility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dailynotes.R
import com.example.dailynotes.databinding.NotesDisplayFragmentBinding
import com.example.dailynotes.database.NotesDao
import com.example.dailynotes.database.NotesDatabase



class NotesDisplayFragment : Fragment() {


    private lateinit var notesDisplayViewModel: NotesDisplayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //get reference to binding and infalte fragment
        val binding: NotesDisplayFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.notes_display_fragment,container,false)

        val application: Application = requireNotNull(this.activity).application
        val datasource: NotesDao = NotesDatabase.getInstance(application).notesDao
        val viewModelFactory = NotesDisplayViewModelFactory(datasource,application)

        notesDisplayViewModel = ViewModelProvider(this,viewModelFactory).get(NotesDisplayViewModel::class.java)

        binding.lifecycleOwner = this

        binding.notesDisplayViewModel = notesDisplayViewModel




        notesDisplayViewModel.navigateToCreateFragment.observe(viewLifecycleOwner, Observer {
            if(it)
            {

                findNavController().navigate(R.id.action_notesDisplayFragment_to_notesCreateFragment)
                notesDisplayViewModel.onNavigateToCreateComplete()
            }
        })

        val adapter = NotesDisplayAdapter(NotesDisplayAdapter.NoteClickListner{
            notesDisplayViewModel.onNoteClicked(it)
        }

        )
        binding.notesList.adapter = adapter



        //Update adpter on Notes List update
        notesDisplayViewModel.allNotes.observe(viewLifecycleOwner, Observer {

          if(it.isNotEmpty())
          {
              adapter.submitList(it)
          }else{
              binding.blankAdapterImageView.visibility = View.VISIBLE
          }
        })

        //Observe navigateToUpdate

        notesDisplayViewModel.navigateToUpdateFragment.observe(viewLifecycleOwner, Observer {

           it?.let {

               findNavController().navigate(NotesDisplayFragmentDirections.actionNotesDisplayFragmentToNotesUpdateFragment(it))
               notesDisplayViewModel.onNavigationToUpdateComplete()
           }
            })



        return binding.root
    }





}
