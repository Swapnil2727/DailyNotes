package com.example.dailynotes.notesdisplay


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.dailynotes.R
import com.example.dailynotes.databinding.NotesDisplayFragmentBinding
import database.NotesDao
import database.NotesDatabase

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
                Toast.makeText(context,"Add Clicked",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_notesDisplayFragment_to_notesCreateFragment)
                notesDisplayViewModel.onNavigateToCreateComplete()
            }
        })




        return binding.root
    }





}
