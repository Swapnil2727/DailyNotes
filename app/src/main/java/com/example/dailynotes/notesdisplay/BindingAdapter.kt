package com.example.dailynotes.notesdisplay

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Transformations
import com.example.dailynotes.database.Notes
import com.example.dailynotes.smartTruncate

@BindingAdapter("setTitle")
fun TextView.setTitle(notes: Notes)
{
    notes?.let{
        text = notes.noteTitle
    }
}

@BindingAdapter("setDetail")
fun TextView.setDetail(notes: Notes)
{
    notes?.let {
        text = notes.noteDetail
    }
}

//@BindingAdapter("getTitle")
//fun EditText.setText(notes:Notes){
//    notes?.let {
//
//}