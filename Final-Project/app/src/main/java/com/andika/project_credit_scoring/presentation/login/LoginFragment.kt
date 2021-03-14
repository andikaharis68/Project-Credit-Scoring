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
import com.andika.project_credit_scoring.Constanst.TOKEN
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentLoginBinding
import com.andika.project_credit_scoring.login.RequestLogin
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var requestLoginValue: RequestLogin
    
    @Inject
    lateinit var sharedPref: SharedPreferences
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
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
                                    sharedPref.edit().putString(TOKEN, "${it.data?.token}")
                                        .apply()
                                    findNavController().navigate(R.id.action_global_homeFragment)
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
                                        "Password or Email invalid!",
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