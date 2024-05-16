package com.example.animalkingdomexplorerapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorerapp.data.database.AppDatabase
import com.example.animalkingdomexplorerapp.data.model.Species
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SpeciesRepository: CoroutineScope { // Inheriting from Coroutines

    //Retrieving and inserting the data in to the DB
    companion object {
        private var appDatabase: AppDatabase? = null

        // Initialization the DB
        private fun initDB(context: Context): AppDatabase {
            return AppDatabase.invoke(context)
        }

        //Inserting the data
        fun insert(context: Context, species: Species){
            appDatabase = initDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                insertSpeciesBG(appDatabase!!, species)
            }
        }

        //Getting the data
        fun getAllSpeciesData(context: Context): LiveData<List<Species>> {
            appDatabase = initDB(context)

            return appDatabase!!.getSpeciesDao().getAllSpecies()
        }

        private suspend fun insertSpeciesBG(appDB: AppDatabase, species: Species){
            //Using the Dispatchers.IO coroutine to save the data
            withContext(Dispatchers.IO){
                appDB.getSpeciesDao().addSpecies(species)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}