package com.kirandroid.mynotes.screens

import android.util.Log
import android.util.Log.d
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kirandroid.mynotes.ui.theme.lightBlue
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("login_screen")
    }

    // Displaying logo
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = lightBlue)
    ) {

        Image(
            painter = painterResource(id = com.kirandroid.mynotes.R.drawable.splash_logo),
            contentDescription = "Logo",
            modifier = Modifier.width(500.dp).height(450.dp)
        )
    }

}