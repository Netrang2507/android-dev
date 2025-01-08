package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserPreferencesScreen(dataStore = applicationContext.userPreferencesDataStore)
        }
    }


}
@Composable
fun UserPreferencesScreen(dataStore: DataStore<UserPreferences>) {
    val userPreferencesState = getUserPreferences(dataStore).collectAsState(initial = UserPreferences.getDefaultInstance())

    var name by remember { mutableStateOf(TextFieldValue(userPreferencesState.value.name)) }
    var age by remember { mutableStateOf(TextFieldValue(userPreferencesState.value.age.toString())) }
    var isPremium by remember { mutableStateOf(userPreferencesState.value.isPremium) }

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Premium Status")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = isPremium,
                onCheckedChange = { isPremium = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedName = name.text
            val updatedAge = age.text.toIntOrNull() ?: 0
            coroutineScope.launch {
                setUserPreferences(dataStore, updatedName, updatedAge, isPremium)
            }
        }) {
            Text(text = "Update Preferences")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Current Preferences:")
        Text(text = "Name: ${userPreferencesState.value.name}")
        Text(text = "Age: ${userPreferencesState.value.age}")
        Text(text = "Premium Status: ${if (userPreferencesState.value.isPremium) "Yes" else "No"}")
    }
}

