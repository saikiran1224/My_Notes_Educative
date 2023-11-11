package com.kirandroid.mynotes.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kirandroid.mynotes.screens.HomeScreen
import com.kirandroid.mynotes.screens.LoginScreen
import com.kirandroid.mynotes.screens.ManageNotesScreen
import com.kirandroid.mynotes.screens.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
           SplashScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
           LoginScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
          HomeScreen(navController = navController)
        }

        composable(route = Screen.ManageNotes.route) {
           ManageNotesScreen(navController = navController)
        }

    }

}