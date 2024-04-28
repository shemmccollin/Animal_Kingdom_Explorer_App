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

class SpeciesRepository: CoroutineScope {
    companion object {
        private var appDatabase: AppDatabase? = null

        private fun initDB(context: Context): AppDatabase {
            return AppDatabase.invoke(context)
        }

        fun insert(context: Context, species: Species){
            appDatabase = initDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                insertSpeciesBG(appDatabase!!, species)
            }
        }

        fun getAllSpeciesData(context: Context): LiveData<List<Species>> {
            appDatabase = initDB(context)

            return appDatabase!!.getSpeciesDao().getAllSpecies()
        }

        private suspend fun insertSpeciesBG(appDB: AppDatabase, species: Species){
            withContext(Dispatchers.IO){
                appDB.getSpeciesDao().addSpecies(species)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}