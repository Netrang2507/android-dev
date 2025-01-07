package com.example.simplesql
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel(private val databaseHelper: DatabaseHelper) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    fun getAllNotes() {
        _notes.value = databaseHelper.getAllNotes()
    }

    fun addNote(title: String, content: String) {
        databaseHelper.insertNote(title, content)
        getAllNotes()
    }

    fun updateNote(id: Long, title: String, content: String) {
        databaseHelper.updateNote(id, title, content)
        getAllNotes()
    }

    fun deleteNote(id: Long) {
        databaseHelper.deleteNote(id)
        getAllNotes()
    }
}
