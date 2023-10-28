package com.faza.challenge_4.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.faza.challenge_4.R
import com.faza.challenge_4.databinding.ActivityMainBinding
import com.faza.challenge_4.util.LoginViewModelFactory
import com.faza.challenge_4.viewModel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener {_,destination,_ ->
            when(destination.id){
                R.id.detailFragment -> hideBotNav()
                R.id.orderFragment -> hideBotNav()
                else -> shotBotNav()
            }
        }
    }

    private fun shotBotNav(){
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
    private fun hideBotNav(){
        binding.bottomNavigationView.visibility = View.GONE
    }

}