package com.andika.project_credit_scoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.andika.project_credit_scoring.Constanst.MENU_ACCOUNT
import com.andika.project_credit_scoring.Constanst.MENU_HISTORY
import com.andika.project_credit_scoring.Constanst.MENU_HOME
import com.andika.project_credit_scoring.Constanst.MENU_TRANSACTION
import com.andika.project_credit_scoring.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            bottomNav.setOnNavigationItemSelectedListener {
                Log.d("NAV", "$it")
                when ("$it") {
                    MENU_HOME -> navHostFragment.findNavController().navigate(R.id.action_global_homeFragment)
                    MENU_TRANSACTION -> navHostFragment.findNavController().navigate(R.id.action_global_transactionFragment)
                    MENU_HISTORY -> navHostFragment.findNavController().navigate(R.id.action_global_historyFragment)
                    MENU_ACCOUNT -> navHostFragment.findNavController().navigate(R.id.action_global_accountFragment)
                }
                true
            }
        }
        setContentView(binding.root)
    }
}