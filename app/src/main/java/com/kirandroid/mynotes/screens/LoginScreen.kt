package com.kirandroid.mynotes.screens

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.kirandroid.mynotes.R
import com.kirandroid.mynotes.ui.theme.darkBlue
import com.kirandroid.mynotes.ui.theme.lightBlue
import com.kirandroid.mynotes.ui.theme.lightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val errorDialog = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center, // aligning all the contents of the Box vertically and horizontally centered
        modifier = Modifier
            .fillMaxSize() // occupying the maximum width and height of the user screen
            .background(color = lightBlue) // setting the background color to Light Blue
    ) {

        // declaring variables to store data
        var textEmailID by remember { mutableStateOf("") }
        var textPassword by remember { mutableStateOf("") }

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

        Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                // Displaying Login here text
                Text(text = "Login here!",
                fontSize = 35.sp,
                color = lightYellow,
                fontWeight = FontWeight.Bold)

                // Email ID - OutlinedTextField
                OutlinedTextField(
                    value = textEmailID,
                    modifier = Modifier.padding(top = 70.dp, bottom = 16.dp),
                    onValueChange = { textEmailID = it },
                    label = { Text("Email ID") },
                    colors = outlinedTextFieldColors,
                    shape = RoundedCornerShape(15.dp)
                )

                // Password - OutlinedTextField
                OutlinedTextField(
                    value = textPassword,
                    onValueChange = { textPassword = it },
                    modifier = Modifier.padding(top = 16.dp, bottom = 70.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text("Password") },
                    colors = outlinedTextFieldColors,
                    shape = RoundedCornerShape(15.dp)
                )

                // Sign-in Button
                ElevatedButton(
                    onClick = {
                        
                           // checking user credentials
                        if (textEmailID == "saikiran" && textPassword == "123456") {
                            navController.navigate("home_screen")
                        } else {
                            // show animation - wrong password
                            errorDialog.value = true
                        }
                      },
                    colors = ButtonDefaults.buttonColors(

                        disabledContainerColor = Color.LightGray,
                        containerColor = Color.White
                    ),
                    modifier = Modifier.width(150.dp),
                    enabled = textEmailID.isNotEmpty() && textPassword.isNotEmpty()) {
                    Text(text = "Sign in", fontSize = 15.sp, color = darkBlue)
                }

            if (errorDialog.value) {
                DialogWithImage(
                    onDismissRequest = { errorDialog.value = false },
                    onConfirmation = {
                        errorDialog.value = false
                        println("Confirmation registered") // Add logic here to handle confirmation.
                    },
                    painter = painterResource(id = R.drawable.splash_logo),
                    imageDescription = "Sample",
                )
            }


            }
    }


}

@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                //  LottieAnimation(composition = , progress = { /*TODO*/ })
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(160.dp)
                )
                Text(
                    text = "This is a dialog with buttons and an image.",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}