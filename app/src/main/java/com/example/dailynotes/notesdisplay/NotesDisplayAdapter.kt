package com.example.dailynotes.notesdisplay


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynotes.databinding.CardNoteBinding
import com.example.dailynotes.database.Notes

class NotesDisplayAdapter (val clickListener: NoteClickListner): androidx.recyclerview.widget.ListAdapter
<Notes,NotesDisplayAdapter.ViewHolder>(NotesDiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position)!!,clickListener)
    }


    class ViewHolder private constructor(val binding:CardNoteBinding ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Notes,
            clickListener: NoteClickListner
        )
        {
            binding.clickListner = clickListener
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

    class NoteClickListner(val clickListener: (notes: Notes) -> Unit)
    {
        fun onClick(notes: Notes)
        {
            clickListener(notes)
        }
    }







}

