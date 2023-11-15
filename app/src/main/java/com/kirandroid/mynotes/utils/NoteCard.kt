package com.kirandroid.mynotes.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.ui.theme.darkBlue
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow

@Composable
fun NoteCard(noteData: Note, navController: NavController) {

    OutlinedCard(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
        .fillMaxWidth()
        .clickable {
            // passing to Manage Notes screen to able user to either update/delete notes
            navController.navigate("manage_notes_screen")
        },
        border = BorderStroke(width = 1.dp, color = lightYellow),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White

        ), content = {

            // Title of notes
            Text(text = noteData.title, fontSize = 18.sp, color = darkBlue, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 18.dp, bottom = 5.dp))

            // Description
            Text(text = noteData.description, fontSize = 16.sp, color = lightBlue, fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 0.dp, bottom = 18.dp))
        })
}