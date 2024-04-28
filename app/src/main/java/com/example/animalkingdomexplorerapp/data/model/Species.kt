package com.example.animalkingdomexplorerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "species")
data class Species(
    val name: String,
    val description: String
):Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}