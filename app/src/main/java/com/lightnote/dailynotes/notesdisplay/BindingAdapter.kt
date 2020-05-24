package com.lightnote.dailynotes.notesdisplay


import android.widget.TextView
import androidx.databinding.BindingAdapter

import com.lightnote.dailynotes.database.Notes


@BindingAdapter("setTitle")
fun TextView.setTitle(notes: Notes)
{
    notes.let{
        text = notes.noteTitle
    }
}

@BindingAdapter("setDetail")
fun TextView.setDetail(notes: Notes)
{
    notes.let {
        text = notes.noteDetail
    }
}

