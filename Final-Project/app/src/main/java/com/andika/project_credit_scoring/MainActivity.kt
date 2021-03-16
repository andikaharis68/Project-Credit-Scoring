package com.andika.project_credit_scoring

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.andika.project_credit_scoring.Constanst.MENU_ACCOUNT
import com.andika.project_credit_scoring.Constanst.MENU_HISTORY
import com.andika.project_credit_scoring.Constanst.MENU_HOME
import com.andika.project_credit_scoring.Constanst.MENU_TRANSACTION
import com.andika.project_credit_scoring.databinding.ActivityMainBinding
import com.andika.project_credit_scoring.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        val maxHeight = binding.layoutActivity.height
        binding.apply {
            navHostFragment.minimumHeight = maxHeight
            bottomNav.setOnNavigationItemSelectedListener {
                Log.d("NAV", "$it")
                when ("$it") {
                    MENU_HOME -> navHostFragment.findNavController().navigate(R.id.action_global_homeFragment)
                    MENU_TRANSACTION -> {
                        navHostFragment.findNavController().navigate(R.id.action_global_transactionFragment)
                    }
                    MENU_HISTORY -> navHostFragment.findNavController().navigate(R.id.action_global_historyFragment)
                    MENU_ACCOUNT -> navHostFragment.findNavController().navigate(R.id.action_global_accountFragment)
                }
                true
            }
        }
        setContentView(binding.root)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun subscribe(){
        viewModel.bottomVisibility.observe(this) {
            binding.bottomNav.visibility = it
        }
    }
}