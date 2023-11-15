package com.kirandroid.mynotes.viewmodels

import android.app.Application
import android.app.LocaleConfig
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.data.NotesDatabase
import com.kirandroid.mynotes.data.NotesRepository


class HomeScreenViewModel(application: Application): AndroidViewModel(application = application) {

    val noteDao = NotesDatabase.getDatabase(application.applicationContext).noteDao()

    var notesList: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()










}