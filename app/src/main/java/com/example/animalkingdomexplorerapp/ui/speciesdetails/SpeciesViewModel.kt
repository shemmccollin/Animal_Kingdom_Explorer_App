package com.example.animalkingdomexplorerapp.ui.speciesdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.animalkingdomexplorerapp.data.model.Species
import com.example.animalkingdomexplorerapp.data.repository.SpeciesRepository

class SpeciesViewModel: ViewModel() {
    fun getAllSpecies(context: Context): LiveData<List<Species>>{
        return SpeciesRepository.getAllSpeciesData(context)
    }

    fun insertSpecies(context: Context, species: Species){
        SpeciesRepository.insert(context, species)
    }
}