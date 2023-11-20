package com.kirandroid.mynotes.screens

import android.annotation.SuppressLint
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kirandroid.mynotes.R
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.data.NotesDatabase
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow
import com.kirandroid.mynotes.utils.NoteCard
import com.kirandroid.mynotes.viewmodels.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections.addAll


@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController, homeScreenViewModel: HomeScreenViewModel) {

    // Created empty list with remember scope to store notes
    val notesList = remember { mutableListOf<Note>() }

    var newNotes = remember {
        mutableStateListOf<Note>()
    }

    val composition by
    rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId = R.raw.no_files_found))


    CoroutineScope(Dispatchers.IO).launch {

        // accessing database
        notesList.clear()
        val noteDao = NotesDatabase.getDatabase(navController.context).noteDao()
        notesList.addAll(noteDao.getAllNotes())

    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(lightBlue)){

        // displaying Column
        Column(modifier = Modifier.padding(15.dp).fillMaxSize()) {

            // creating to row to display icon for create notes
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // displaying My Notes text
                Text(
                    text = "My Notes",
                    textAlign = TextAlign.Start,
                    color = lightYellow,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.9f)
                )

                // making the Icon clickable
                IconButton(onClick = {
                    // navigate user to create notes
                    navController.navigate("create_note_screen")
                }) {
                    // displaying Icon inside IconButton
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_note_add_24),
                        contentDescription = "Create notes",
                        tint = lightYellow,
                        modifier = Modifier
                            .size(34.dp)
                            .weight(0.1f),
                    )
                } // end of Icon Button

            } // end of Row()

            val showNotesList = remember { mutableStateOf(false) }

            // Performing Async task to load Database and retrieve items
           LaunchedEffect(key1 = Unit) {
               withContext(Dispatchers.Main) {

                   if (notesList.isNotEmpty()) {
                       Log.d("TAG", "List of notes: $notesList")
                       showNotesList.value = true

                       newNotes.addAll(notesList.toMutableStateList())

                      // Log.d("TAG", "inside Lazy Column : $notesList")
                   }

               }
           }

            // if there is no data in notes list
            if (showNotesList.value) {
               Log.d("TAG", "New notes: ${newNotes.toList()}")
                // displaying LazyColumn if there is data in notesList
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 15.dp, end = 5.dp),
                    content = {
                        items(newNotes.toList()) {
                            NoteCard(noteData = it, navController = navController)
                        }
                    })
            } else {

                // will be showing no data
                Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {

                    LottieAnimation(
                        modifier = Modifier.size(width = 270.dp, height = 350.dp).padding(top = 150.dp),
                        composition = composition,
                        iterations = LottieConstants.IterateForever)

                    Text(
                        text = "Add notes using + Button on top right",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontSize = 15.sp
                    )
                }

            }
        }
    }
}






/*fun getNotesData(navController: NavController): List<Note> {

    var notesList = emptyList<Note>()
    val noteDao = NotesDatabase.getDatabase(navController.context).noteDao()

    CoroutineScope(Dispatchers.IO).launch {

        notesList = noteDao.getAllNotes()

    }

    Toast.makeText(navController.context, "", Toast.LENGTH_LONG).show()
    Log.d("notesList", notesList.toString())
    return notesList

}*/
