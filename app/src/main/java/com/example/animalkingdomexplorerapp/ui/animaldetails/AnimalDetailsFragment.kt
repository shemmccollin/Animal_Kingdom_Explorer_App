package com.example.animalkingdomexplorerapp.ui.animaldetails

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorerapp.R
import com.example.animalkingdomexplorerapp.data.model.Animal
import com.example.animalkingdomexplorerapp.databinding.FragmentAnimalDetailsBinding
import com.example.animalkingdomexplorerapp.toast

class AnimalDetailsFragment : Fragment() {
    //Setup the view binding for this fragment
    private lateinit var binding: FragmentAnimalDetailsBinding

    //Setup the RecyclerView, Adapter and viewModal
    private lateinit var animalRV: RecyclerView
    private lateinit var animalAdapter: AnimalAdapter
    private lateinit var animalViewModal: AnimalViewModel

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_details, container, false)

       binding = FragmentAnimalDetailsBinding.bind(view)

       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set the viewmodal with the viewmodalprovider
        animalViewModal = ViewModelProvider(this)[AnimalViewModel::class.java]
        animalRV = binding.animalRecycler
        animalAdapter = AnimalAdapter(ArrayList())

        //set the recycler view with the layout manager and the adapter
        animalRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = animalAdapter
        }

        //Setup a viewmodal observe to update when ever data changes
        animalViewModal.getAllAnimals(requireContext()).observe(viewLifecycleOwner) {
            animalAdapter.setData(it as ArrayList<Animal>)
        }

        binding.animalFloatingBtn.setOnClickListener{//Setup the on click event for the floating button, to open a dialog to add data
            val mView = getLayoutInflater().inflate(R.layout.new_animal, null)
            val name = mView.findViewById<EditText>(R.id.animalNewName)
            val habitat = mView.findViewById<EditText>(R.id.animalNewHabitat)
            val diet = mView.findViewById<EditText>(R.id.animalNewDiet)

            //Setting up the dialog with a custom view
            val dialog: AlertDialog = AlertDialog.Builder(context)
                .setTitle(getString(R.string.add_animal))
                .setIcon(R.drawable.animal)
                .setView(mView)
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel", ) {dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {

                //Error checking
                if(name.text.toString().isBlank())
                {
                    name.error = "Name required"
                    name.requestFocus()
                    return@setOnClickListener
                }

                if(habitat.text.toString().isBlank())
                {
                    habitat.error = "Habitat required"
                    habitat.requestFocus()
                    return@setOnClickListener
                }

                if(diet.text.toString().isBlank())
                {
                    diet.error = "Diet required"
                    diet.requestFocus()
                    return@setOnClickListener
                }

                //Starts the the insert animal function to save the data from the modal
                animalViewModal.insertAnimal(requireContext(),
                    Animal(
                        name.text.toString(),
                        habitat.text.toString(),
                        diet.text.toString()
                    )
                )

                //Dismiss the dialog
                dialog.dismiss()
                context?.toast("Animal Saved")
            }
        }
    }
}