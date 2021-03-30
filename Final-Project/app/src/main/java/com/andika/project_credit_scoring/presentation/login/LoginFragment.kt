package com.andika.project_credit_scoring.presentation.login

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentLoginBinding
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.util.Constanst.APPROVAL_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.FULLNAME
import com.andika.project_credit_scoring.util.Constanst.INPUT_CUSTOMER
import com.andika.project_credit_scoring.util.Constanst.INPUT_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.MASTER
import com.andika.project_credit_scoring.util.Constanst.READ_ALL_CUSTOMER
import com.andika.project_credit_scoring.util.Constanst.READ_ALL_REPORT
import com.andika.project_credit_scoring.util.Constanst.READ_ALL_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.READ_REPORT_BY_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.ROLE
import com.andika.project_credit_scoring.util.Constanst.SUPERVISOR
import com.andika.project_credit_scoring.util.Constanst.USERNAME
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var requestLoginValue: RequestLogin
    lateinit var sharedViewModel: MainActivityViewModel
    lateinit var loadingDialog: AlertDialog

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
        loadingDialog = LoadingDialog.build(requireContext())
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
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.getValidation().observe(this) { itValid ->
            loadingDialog.show()
            binding.apply {
                if (!itValid.password && itValid.username) {
                    findNavController().navigate(R.id.action_global_homeMasterFragment)
                    sharedViewModel.hideBottomVav(true)
                    viewModel.postLogin(requestLoginValue).observe(requireActivity()) {
                        when (it?.code) {
                            200 -> {
                                loadingDialog.hide()
                                if (it?.data?.roles == MASTER ) {
                                    sharedPref.edit()
                                        .putString(TOKEN, "${it.data?.token}")
                                        .putString(FULLNAME, "${it.data?.fullName}")
                                        .putString(ROLE, "${it.data?.roles}")
                                        .apply()
                                    findNavController().navigate(R.id.action_global_homeMasterFragment)
                                    sharedViewModel.hideBottomVav(true)
                                } else {
                                    sharedPref.edit()
                                        .putString(TOKEN, "${it.data?.token}")
                                        .putString(FULLNAME, "${it.data?.fullName}")
                                        .putString(ROLE, "${it.data?.roles}")
                                        .putString(READ_ALL_TRANSACTION, "${it.data?.readAllTransaction}")
                                        .putString(APPROVAL_TRANSACTION, "${it.data?.approveTransaction}")
                                        .putString(READ_ALL_REPORT, "${it.data?.readAllReport}")
                                        .apply()
                                    findNavController().navigate(R.id.action_global_homeFragment)
                                    sharedViewModel.hideBottomVav(true)
                                }
                            }
                            100 -> {
                                loadingDialog.hide()
                                Toast.makeText(
                                    requireContext(),
                                    "${it?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                loadingDialog.hide()
                                Toast.makeText(
                                    requireContext(),
                                    "${it?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        "Invalid password, please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}