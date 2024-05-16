package com.example.animalkingdomexplorerapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.animalkingdomexplorerapp.data.database.AppDatabase
import com.example.animalkingdomexplorerapp.data.model.Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AnimalRepository: CoroutineScope {// Inheriting from Coroutines

    //Retrieving and inserting the data in to the DB
    companion object {
        private var appDatabase: AppDatabase? = null

        // Initialization the DB
        private fun initDB(context: Context): AppDatabase {
            return AppDatabase.invoke(context)
        }

        //Inserting the data
        fun insert(context: Context, animal :Animal){
            appDatabase = initDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                insertAnimalBG(appDatabase!!, animal)
            }
        }

        //Getting the data
        fun getAllAnimalData(context: Context): LiveData<List<Animal>> {
            appDatabase = initDB(context)

            return appDatabase!!.getAnimalDao().getAllAnimals()
        }

        private suspend fun insertAnimalBG(appDB: AppDatabase, animal: Animal){
            //Using the Dispatchers.IO coroutine to save the data
            withContext(Dispatchers.IO){
                appDB.getAnimalDao().addAnimal(animal)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}