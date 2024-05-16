package com.example.animalkingdomexplorerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "animals")
data class Animal(
    val name: String,
    val habitat: String,
    val diet: String
):Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}