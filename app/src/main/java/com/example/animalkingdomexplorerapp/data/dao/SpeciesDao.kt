package com.example.animalkingdomexplorerapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorerapp.data.model.Animal
import com.example.animalkingdomexplorerapp.data.model.Species

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species ORDER BY id DESC")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE id = :speciesId")
    fun getSpeciesById(speciesId: Long): Species

    @Insert
    fun addSpecies(species: Species)
}