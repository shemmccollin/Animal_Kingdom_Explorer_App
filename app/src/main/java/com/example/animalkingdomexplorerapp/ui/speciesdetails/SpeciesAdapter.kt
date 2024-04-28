package com.example.animalkingdomexplorerapp.ui.speciesdetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorerapp.R
import com.example.animalkingdomexplorerapp.data.model.Animal
import com.example.animalkingdomexplorerapp.data.model.Species

class SpeciesAdapter(private var species: ArrayList<Species>):RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpeciesAdapter.SpeciesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.species_item, parent, false)
        return SpeciesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SpeciesAdapter.SpeciesViewHolder, position: Int) {
        val spec = species[position]

        holder.speciesName .text = spec.name
        holder.speciesDescription .text = spec.description
    }

    override fun getItemCount(): Int {
        return species.size
    }

    fun setData(speciesList: ArrayList<Species>)
    {
        this.species = speciesList
        notifyDataSetChanged()
    }

    inner class SpeciesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val speciesName: TextView = itemView.findViewById(R.id.species_name)
        val speciesDescription: TextView = itemView.findViewById(R.id.species_description)
    }
}