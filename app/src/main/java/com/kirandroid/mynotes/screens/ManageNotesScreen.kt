package com.kirandroid.mynotes.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.data.NotesDatabase
import com.kirandroid.mynotes.ui.theme.darkBlue
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun ManageNotesScreen(navController: NavController) {


    // Initialize HomeScreen View model and retrieve notes list from Room Database
    val noteDao = NotesDatabase.getDatabase(navController.context).noteDao()

    val coroutineScope = MainScope()

    Box(modifier = Modifier.fillMaxSize()
        .background(lightBlue)) {

        // declaring variables to store data
        var textNoteTitle by remember { mutableStateOf("") }
        var textNoteDescription by remember { mutableStateOf("") }

        // declaring colors field for OutlinedTextField
        val outlinedTextFieldColors: TextFieldColors = TextFieldDefaults.colors(

            unfocusedIndicatorColor = Color.White,
            unfocusedLabelColor = Color.White,
            unfocusedContainerColor = lightBlue,

            focusedIndicatorColor = lightYellow,
            focusedLabelColor = lightYellow,
            focusedContainerColor = lightBlue,

            focusedTextColor = lightYellow,
            unfocusedTextColor = Color.White,

            cursorColor = lightYellow,
        )

        // displaying Column
        Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            // displaying My Notes text
            Text(text = "Make a note here!",
                textAlign = TextAlign.Start,
                color = lightYellow,
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 30.dp),
                fontWeight = FontWeight.Bold)

            // Title of notes
            OutlinedTextField(
                value = textNoteTitle,
                modifier = Modifier.fillMaxWidth().padding(top = 35.dp, bottom = 25.dp),
                onValueChange = { textNoteTitle = it },
                label = { Text("Title") },
                colors = outlinedTextFieldColors,
                shape = RoundedCornerShape(15.dp),
            )

            // Note description
            OutlinedTextField(
                value = textNoteDescription,
                modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                onValueChange = { textNoteDescription = it },
                label = { Text("Description") },
                colors = outlinedTextFieldColors,
                shape = RoundedCornerShape(15.dp),
                minLines = 7
            )

            // Sign-in Button
            ElevatedButton(onClick = {

                // sending data to Room DB
                coroutineScope.launch {
                    //Toast.makeText(navController.context, "" + textNoteTitle + " " + textNoteDescription, Toast.LENGTH_LONG).show()
                    noteDao.insert(Note(title = textNoteTitle, description = textNoteDescription))
                    navController.navigate("home_screen")
                }
            },
            modifier = Modifier.width(150.dp)) {

                Text(text = "Save Notes", fontSize = 14.sp, color = darkBlue)
            }
        }

    }

}