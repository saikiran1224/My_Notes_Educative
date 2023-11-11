package com.kirandroid.mynotes.navigation


sealed class Screen(val route: String) // add `route` as parameter
{

    // Use this syntax to create a object
    // object ClassName: Screen("screen_name")

    object Splash: Screen("splash_screen")
    object Login: Screen("login_screen")
    object Home: Screen("home_screen")
    object ManageNotes: Screen("manage_notes_screen")

}
