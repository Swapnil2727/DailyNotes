package com.example.dailynotes.notesdisplay

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dailynotes.database.Notes

@BindingAdapter("setTitle")
fun TextView.setTitle(notes: Notes)
{
    notes?.let{
        text = notes.noteTitle.toString()
    }
}

@BindingAdapter("setDetail")
fun TextView.setDetail(notes: Notes)
{
    notes?.let {
        text = notes.noteDetail.toString()
    }
}