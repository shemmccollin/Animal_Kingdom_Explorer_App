package com.example.animalkingdomexplorerapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.animalkingdomexplorerapp.R
import com.example.animalkingdomexplorerapp.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        binding = FragmentDashboardBinding.bind(view)

        binding.apply {
            animalBtn.setOnClickListener{
                //val directions = DashboardFragmentDirections.actionDashboardFragmentToAnimalDetailsFragment()
                findNavController().navigate(R.id.action_dashboardFragment_to_animalDetailsFragment)
            }

            speciesBtn.setOnClickListener{
                //val directions = DashboardFragmentDirections.actionDashboardFragmentToSpeciesDetailsFragment()
                findNavController().navigate(R.id.action_dashboardFragment_to_speciesDetailsFragment)
            }
        }

        return binding.root
    }
}