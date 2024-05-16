package com.example.animalkingdomexplorerapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorerapp.data.model.Animal

//This is the Data Access Object for Animal, it defines the functionality expected from the DB
@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals ORDER BY id DESC")
    fun getAllAnimals(): LiveData<List<Animal>>
    @Insert
    suspend fun addAnimal(animal: Animal)
}