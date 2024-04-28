package com.example.animalkingdomexplorerapp.ui.animaldetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorerapp.R
import com.example.animalkingdomexplorerapp.data.model.Animal

class AnimalAdapter(private var animals: ArrayList<Animal>):RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalAdapter.AnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalAdapter.AnimalViewHolder, position: Int) {
        val animal = animals[position]

        holder.animalName.text = animal.name
        holder.animalHabitat.text = animal.habitat
        holder.animalDiet.text = animal.diet
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    fun setData(animalList: ArrayList<Animal>)
    {
        this.animals = animalList
        notifyDataSetChanged()
    }

    inner class AnimalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val animalName: TextView = itemView.findViewById(R.id.animal_name)
        val animalHabitat: TextView = itemView.findViewById(R.id.animal_habitat)
        val animalDiet: TextView = itemView.findViewById(R.id.animal_diet)
    }
}