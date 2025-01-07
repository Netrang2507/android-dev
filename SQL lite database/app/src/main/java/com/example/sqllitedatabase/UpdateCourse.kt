package com.example.sqllitedatabase

import DBHandler
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sqllitedatabase.ui.theme.greenColor

class UpdateCourse : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                        updateDataToDatabase(
                            LocalContext.current,
                            intent.getStringExtra("courseName"),
                            intent.getStringExtra("courseDuration"),
                            intent.getStringExtra("courseTracks"),
                            intent.getStringExtra("courseDescription")
                        )
                    }
                }
            }




@Composable
fun updateDataToDatabase(
    context: Context,
    cName: String?,
    cTracks: String?,
    cDuration: String?,
    cDescription: String?
) {
    val activity = context as Activity
    var courseName = remember { mutableStateOf(cName) }
    val courseDuration = remember { mutableStateOf(cDuration) }
    val courseTracks = remember { mutableStateOf(cTracks) }
    val courseDescription = remember { mutableStateOf(cDescription) }

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var dbHandler: DBHandler = DBHandler(context)
        Text(
            text = "SQlite Database in Android",
            color = greenColor, fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseName.value!!,
            onValueChange = { courseName.value = it },
            placeholder = { Text(text = "Enter your course name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseDuration.value!!,
            onValueChange = { courseDuration.value = it },
            placeholder = { Text(text = "Enter your course duration") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseTracks.value!!,
            onValueChange = { courseTracks.value = it },
            placeholder = { Text(text = "Enter your course tracks") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseDescription.value!!,
            onValueChange = { courseDescription.value = it },
            placeholder = { Text(text = "Enter your course description") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            dbHandler.updateCourse(
                cName!!,
                courseName.value,
                courseDescription.value,
                courseTracks.value,
                courseDuration.value
            )
            Toast.makeText(context, "Course Updated..", Toast.LENGTH_SHORT).show()
            val i = Intent(context, MainActivity::class.java)
            context.startActivity(i)
        }) {
            Text(text = "Update Course", color = Color.White)
        }
    }
}
