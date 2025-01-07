package com.example.simplesql
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = DatabaseHelper(this)
        noteViewModel = ViewModelProvider(this, ViewModelFactory(databaseHelper)).get(NoteViewModel::class.java)

        setContent {
            NoteApp(noteViewModel)
        }
    }
}

@Composable
fun NoteApp(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (title.isNotEmpty() && content.isNotEmpty()) {
                viewModel.addNote(title, content)
                title = ""
                content = ""
            }
        }) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                NoteItem(note, viewModel)
            }
        }
    }
}
@Composable
fun NoteItem(note: Note, viewModel: NoteViewModel) {
    var isEditing by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }

    Column(modifier = Modifier.padding(8.dp)) {
        if (!isEditing) {
            Text(text = note.title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
        } else {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            if (!isEditing) {
                Button(onClick = { isEditing = true }) {
                    Text("Edit")
                }
            } else {
                Button(onClick = {
                    if (title.isNotEmpty() && content.isNotEmpty()) {
                        viewModel.updateNote(note.id, title, content)
                        isEditing = false
                    }
                }) {
                    Text("Update")
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Delete button with Icon
            IconButton(onClick = { viewModel.deleteNote(note.id) }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Note"
                )
            }
        }
    }
}
@Composable
fun update(){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp,10.dp,10.dp,10.dp)){  }
}