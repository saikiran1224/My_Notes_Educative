package com.kirandroid.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey


// Need to add below dependencies
//Room
// implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
// ksp("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
// implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")

// Refer this codelab - https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#3
@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)