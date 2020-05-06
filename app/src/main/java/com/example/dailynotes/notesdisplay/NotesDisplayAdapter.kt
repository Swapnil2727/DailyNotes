package com.example.dailynotes.notesdisplay


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynotes.databinding.CardNoteBinding
import com.example.dailynotes.database.Notes

class NotesDisplayAdapter : androidx.recyclerview.widget.ListAdapter
<Notes,NotesDisplayAdapter.ViewHolder>(NotesDiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position)!!)
    }


    class ViewHolder private constructor(val binding:CardNoteBinding ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notes)
        {

            binding.notes = item
            binding.executePendingBindings()
        }


        companion object{

            fun from(parent: ViewGroup):ViewHolder{
                val binding = CardNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return ViewHolder(binding)
            }

        }
    }
    class NotesDiffCallback: DiffUtil.ItemCallback<Notes>()
    {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
           return oldItem == newItem
        }

    }





}

