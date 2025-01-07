package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.roomDb.Note
import com.example.myapplication.roomDb.NoteDatabase
import com.example.myapplication.viewModel.NoteViewModel
import com.example.myapplication.viewModel.Repository

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            name = "note.db"
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var name by remember { mutableStateOf("") }
            var body by remember { mutableStateOf("") }
            val note = Note(name, body)
            var noteList by remember { mutableStateOf(listOf<Note>()) }
            var showDialog by remember { mutableStateOf(false) }
            var selectedNote by remember { mutableStateOf<Note?>(null) }

            viewModel.getNotes().observe(this) {
                noteList = it
            }

            Column(
                Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = {
                    viewModel.upsertNote(note)
                }) {
                    Text(text = "Add Note")
                }

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text(text = "Name") }
                )

                TextField(
                    value = body,
                    onValueChange = { body = it },
                    placeholder = { Text(text = "Body") }
                )


                LazyColumn {
                    items(noteList) { note ->
                        NoteItem(
                            note = note,
                            viewModel = viewModel,
                            showDialog = showDialog,
                            onUpdateClick = {
                                selectedNote = note
                                name = note.noteName
                                body = note.noteBody
                                showDialog = true
                            }
                        )
                    }
                }

                if (showDialog && selectedNote != null) {
                    UpdateNoteDialog(
                        note = selectedNote!!,
                        onDismiss = { showDialog = false },
                        onSave = { updatedNote ->
                            viewModel.upsertNote(updatedNote)
                            showDialog = false
                        },
                        name = name,
                        body = body,
                        onNameChange = { name = it },
                        onBodyChange = { body = it }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, viewModel: NoteViewModel, showDialog: Boolean, onUpdateClick: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${note.noteName}")
        Spacer(modifier = Modifier.height(6.dp))

        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(6.dp))

        Text(text = "Body: ${note.noteBody}")
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Actions")

            IconButton(onClick = {
                viewModel.deleteNote(note)
            }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Icon")
            }

            IconButton(onClick = {
                onUpdateClick()
            }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit Icon")
            }
        }

    }

}

@Composable
fun UpdateNoteDialog(
    note: Note,
    onDismiss: () -> Unit,
    onSave: (Note) -> Unit,
    name: String,
    body: String,
    onNameChange: (String) -> Unit,
    onBodyChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Note") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = body,
                    onValueChange = onBodyChange,
                    label = { Text("Body") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(note.copy(noteName = name, noteBody = body))
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



