package com.example.animalkingdomexplorerapp.ui.speciesdetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalkingdomexplorerapp.R
import com.example.animalkingdomexplorerapp.data.model.Species
import com.example.animalkingdomexplorerapp.databinding.FragmentSpeciesDetailsBinding
import com.example.animalkingdomexplorerapp.toast

class SpeciesDetailsFragment : Fragment() {
    //Setup the view binding for this fragment
    private lateinit var binding: FragmentSpeciesDetailsBinding

    //Setup the RecyclerView, Adapter and viewModal
    private lateinit var speciesRV: RecyclerView
    private lateinit var speciesAdapter: SpeciesAdapter
    private lateinit var speciesViewModal: SpeciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_species_details, container, false)

        binding = FragmentSpeciesDetailsBinding.bind(view)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set the viewmodal with the viewmodalprovider
        speciesViewModal = ViewModelProvider(this)[SpeciesViewModel::class.java]
        speciesRV = binding.speciesRecycler
        speciesAdapter = SpeciesAdapter(ArrayList())
        //set the recycler view with the layout manager and the adapter
        speciesRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = speciesAdapter
        }

        //Setup a viewmodal observe to update when ever data changes
        speciesViewModal.getAllSpecies(requireContext()).observe(viewLifecycleOwner) {
            speciesAdapter.setData(it as ArrayList<Species>)
        }

        binding.speciesFloatingBtn.setOnClickListener{//Setup the on click event for the floating button, to open a dialog to add data
            val mView = getLayoutInflater().inflate(R.layout.new_species, null)
            val name = mView.findViewById<EditText>(R.id.speciesNewName)
            val description = mView.findViewById<EditText>(R.id.speciesNewDescription)

            //Setting up the dialog with a custom view
            val dialog: AlertDialog = AlertDialog.Builder(context)
                .setTitle(getString(R.string.add_species))
                .setIcon(R.drawable.species)
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

                if(description.text.toString().isBlank())
                {
                    description.error = "Description required"
                    description.requestFocus()
                    return@setOnClickListener
                }

                //Starts the the insert species function to save the data from the modal
                speciesViewModal.insertSpecies(requireContext(),
                    Species(
                        name.text.toString(),
                        description.text.toString(),
                    )
                )

                //Dismiss the dialog
                dialog.dismiss()
                context?.toast("Species Saved")
            }
        }
    }
}