package com.example.animalkingdomexplorerapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.animalkingdomexplorerapp.data.model.Species

//This is the Data Access Object for Species, it defines the functionality expected from the DB
@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species ORDER BY id DESC")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE id = :speciesId")
    fun getSpeciesById(speciesId: Long): Species

    //the suspend keyword here means it is expected to run on a background thread
    @Insert
    suspend fun addSpecies(species: Species)
}