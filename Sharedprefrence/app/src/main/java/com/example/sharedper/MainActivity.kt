package com.example.sharedper

import androidx.compose.runtime.setValue



import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreference = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            PreferenceApp(sharedPreference)
        }
    }
}

@Composable
fun PreferenceApp(sharedPreference: SharedPreferences) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter Name") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text(text = "Enter Age") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Display error message if age is invalid
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Save data to SharedPreferences
        Button(onClick = {
            // Validate the age input
            val ageInt = age.toIntOrNull() // Safely convert age to an integer
            if (ageInt != null) {
                val editor = sharedPreference.edit()
                editor.putString("name", name)
                editor.putInt("age", ageInt) // Store age as an integer
                editor.apply() // Use apply for asynchronous saving
                name = ""
                age = ""
                errorMessage = "" // Clear any previous error message
            } else {
                errorMessage = "Please enter a valid age."
            }
        }) {
            Text(text = "SET PREFS")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Retrieve data from SharedPreferences
        Button(onClick = {
            val savedName = sharedPreference.getString("name", "No Name")
            val savedAge = sharedPreference.getInt("age", 0)

            name = savedName ?: "No Name"
            age = savedAge.toString()
        }) {
            Text(text = "GET PREFS")
        }
    }
}


