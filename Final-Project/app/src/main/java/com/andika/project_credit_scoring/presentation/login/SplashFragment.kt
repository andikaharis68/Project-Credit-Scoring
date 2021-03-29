package com.andika.project_credit_scoring.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentSplashBinding
import com.andika.project_credit_scoring.util.Constanst.MASTER
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var sharedViewModel: MainActivityViewModel

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        sharedViewModel.hideBottomVav(true)
        delaySplash()
        binding = FragmentSplashBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    fun delaySplash(){
        val token = sharedPref.getString("TOKEN", "")
        val role = sharedPref.getString("ROLE", "")
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            if(token == ""){
                findNavController().navigate(R.id.action_global_loginFragment)
            } else {
                if (role == MASTER) {
                    findNavController().navigate(R.id.action_global_homeMasterFragment)
                } else {
                    findNavController().navigate(R.id.action_global_homeFragment)
                }
            }
        }
    }

    fun initViewModel(){
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }
}