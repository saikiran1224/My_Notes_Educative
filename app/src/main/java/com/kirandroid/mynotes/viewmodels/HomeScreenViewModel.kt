package com.kirandroid.mynotes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kirandroid.mynotes.data.Note
import com.kirandroid.mynotes.data.NotesDatabase


class HomeScreenViewModel(application: Application): AndroidViewModel(application = application) {

    val noteDao = NotesDatabase.getDatabase(application.applicationContext).noteDao()

    var notesList: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()










}