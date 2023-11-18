package com.kirandroid.mynotes.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kirandroid.mynotes.R
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.data.NotesDatabase
import com.kirandroid.mynotes.ui.theme.darkBlue
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow
import com.kirandroid.mynotes.ui.theme.paleBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NoteCard(noteData: Note, navController: NavController) {

    // set value to show delete dialog
    val deleteNoteConfirmationDialog = remember { mutableStateOf(false) }

    OutlinedCard(modifier = Modifier
        .padding(top = 8.dp, bottom = 8.dp)
        .fillMaxWidth()
        .clickable {
            // prompt user for confirming to delete the note
            deleteNoteConfirmationDialog.value = true
        },
        border = BorderStroke(width = 1.dp, color = Color.White),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = lightBlue

        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        content = {

            // Title of notes
            Text(text = noteData.title, fontSize = 18.sp, color = lightYellow, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, top = 10.dp, bottom = 5.dp, end = 8.dp))

            // Description
            Text(text = noteData.description, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 8.dp,top = 0.dp, bottom = 10.dp, end = 8.dp))
        })


       // code for DeleteNoteConfirmationDialog - If value set to true
       if(deleteNoteConfirmationDialog.value) {
           // showing the dialog
           DeleteNoteConfirmationDialog(
               onDismissRequest = {
                        // hide the dialog
                        deleteNoteConfirmationDialog.value = false
               }, onConfirmation = {
                   // Run delete query, refresh the list and hide the dialog
                   // Performing Async task to load Database and retrieve items
                   CoroutineScope(Dispatchers.IO).launch {
                       // accessing database
                       val noteDao = NotesDatabase.getDatabase(navController.context).noteDao()
                       noteDao.delete(noteData)
                       deleteNoteConfirmationDialog.value = false
                   }

                   navController.navigate("home_screen")
               })
       }
}


@Composable
fun DeleteNoteConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {

    val composition by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.delete_file_anim))

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(355.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = paleBlue
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                // displaying Delete Animation
                LottieAnimation(
                    modifier = Modifier
                        .size(width = 400.dp, height = 180.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,

                )

                Text(
                    text = "Would you like to delete this note?",
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold
                )

                // Buttons for Dismiss and Confirm
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss", color = darkBlue)
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm", color = darkBlue)
                    }
                }
            }
        }
    }
}