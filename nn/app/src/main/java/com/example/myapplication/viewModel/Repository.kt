package com.example.myapplication.viewModel

import androidx.room.Delete
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Upsert
import com.example.myapplication.roomDb.Note
import com.example.myapplication.roomDb.NoteDatabase

class Repository(private val db : NoteDatabase) {

    suspend fun upsertNote(note: Note){
        db.dao.upsertNote(note)

    }


    suspend fun deleteNote(note: Note){
        db.dao.deleteNote(note)

    }


    fun getAllNotes()=db.dao.getAllNotes()



}