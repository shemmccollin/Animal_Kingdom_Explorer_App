package com.example.animalkingdomexplorerapp.ui.animaldetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.animalkingdomexplorerapp.data.model.Animal
import com.example.animalkingdomexplorerapp.data.repository.AnimalRepository

class AnimalViewModel: ViewModel() {
     fun getAllAnimals(context: Context): LiveData<List<Animal>>{
         return AnimalRepository.getAllAnimalData(context)
    }

    fun insertAnimal(context: Context, animal: Animal){
       AnimalRepository.insert(context, animal)
    }
}