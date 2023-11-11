package com.kirandroid.mynotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirandroid.mynotes.R
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(lightBlue)){

        // displaying Column
        Column(modifier = Modifier.padding(20.dp)) {

            // creating to row to display icon for create notes
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                  // displaying My Notes text
                  Text(text = "My Notes",
                      textAlign = TextAlign.Start,
                      color = lightYellow,
                      fontSize = 28.sp,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.weight(1.8f))

                  // making the Icon clickable
                  IconButton(onClick = {
                      // navigate user to create notes
                      navController.navigate("manage_notes_screen")
                  }) {
                      // displaying Icon inside IconButton
                      Icon(painter = painterResource(id = R.drawable.baseline_note_add_24),
                          contentDescription = "Create notes",
                          tint = lightYellow,
                          modifier = Modifier
                              .size(34.dp)
                              .weight(0.2f),
                      )
                  } // end of Icon Button

              } // end of Row()


            // Displaying HomeBody() by passing NotesList to it
            HomeBody()

        }

    }

}


@Composable
fun HomeBody() {

}