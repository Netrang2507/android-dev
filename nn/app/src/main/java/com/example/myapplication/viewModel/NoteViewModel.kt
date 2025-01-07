package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.roomDb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: Repository):ViewModel() {
    fun getNotes() = repository.getAllNotes().asLiveData(viewModelScope.coroutineContext)

    fun upsertNote(note: Note){

        viewModelScope.launch {
            repository.upsertNote(note)
        }
    }
    fun deleteNote(note: Note){

        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}