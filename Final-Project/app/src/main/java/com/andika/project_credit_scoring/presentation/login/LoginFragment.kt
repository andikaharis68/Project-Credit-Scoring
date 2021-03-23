package com.andika.project_credit_scoring.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentLoginBinding
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.util.Constanst.FULLNAME
import com.andika.project_credit_scoring.util.Constanst.ROLE
import com.andika.project_credit_scoring.util.Constanst.USERNAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var requestLoginValue: RequestLogin
    lateinit var sharedViewModel: MainActivityViewModel

    @Inject
    lateinit var sharedPref: SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        sharedViewModel.hideBottomVav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.apply {
            buttonSubmit.setOnClickListener {
                val username = usernameInputText.text.toString()
                val password = passwordInputText.text.toString()
                requestLoginValue = RequestLogin(username = username, password = password)
                viewModel.validation(requestLoginValue)
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.getValidation().observe(this) { itValid ->
            Log.d("STATUS", "$itValid")
            binding.apply {
                if (itValid.email && itValid.username) {
                    viewModel.postLogin(requestLoginValue)
                        .observe(requireActivity()) {
                            when (it?.code) {
                                200 -> {
                                    sharedPref.edit()
                                        .putString(TOKEN, "${it.data?.token}")
                                        .putString(FULLNAME, "${it.data?.fullName}")
                                        .putString(ROLE, "${it.data?.roles}")
                                        .apply()
                                    findNavController().navigate(R.id.action_global_homeFragment)
                                    sharedViewModel.hideBottomVav(true)
                                }
                                401 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Password or Email invalid! 401",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                400 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Password or Email invalid! 400",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "Password or Email invalid!m",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
    
}