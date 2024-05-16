package com.example.animalkingdomexplorerapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animalkingdomexplorerapp.data.dao.AnimalDao
import com.example.animalkingdomexplorerapp.data.dao.SpeciesDao
import com.example.animalkingdomexplorerapp.data.model.Animal
import com.example.animalkingdomexplorerapp.data.model.Species

//In the AppDatabase we use set the entites with the models of the animal and species class
@Database(
    entities = [Animal::class, Species::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getAnimalDao(): AnimalDao
    abstract fun getSpeciesDao(): SpeciesDao

    //We are building the DB here
    companion object{
        //@Volatile here means that the variables must be read from this specific place in memory every time,
        // this is important when dealing with different threading operations
        @Volatile private var instance : AppDatabase? = null
        private val LOCK = Any()

        // Here the invoke checks if the instance is not null and returns the instance, if its null
        // then it is locked to allow only one operation, it then checks if it is null again and if so it calls the function buildDatabase
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }
        }

        //This is the function to build the database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appDatabase"
        ).build()
    }
}