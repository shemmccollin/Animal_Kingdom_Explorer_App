package com.example.animalkingdomexplorerapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorerapp.data.model.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animals ORDER BY id DESC")
    fun getAllAnimals(): LiveData<List<Animal>>
    @Insert
    fun addAnimal(animal: Animal)
}