package com.kirandroid.mynotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// increase the version everytime when you change the schema
@Database(entities = [Note::class], version = 1, exportSchema = false)

abstract class NotesDatabase : RoomDatabase() {

    // initialising NoteDao
    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var Instance: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NotesDatabase::class.java, "notes1_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}