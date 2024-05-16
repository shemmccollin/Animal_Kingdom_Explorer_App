package com.example.animalkingdomexplorerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.animalkingdomexplorerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Setup the view binding for this activity
    private lateinit var binding: ActivityMainBinding
    //Setup the navController
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflate the layout for this fragment
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Set the content view to the view binding root
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    //This allows for when the back arrow is clicked in the navigation bar for it to navigate to the previous view
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}