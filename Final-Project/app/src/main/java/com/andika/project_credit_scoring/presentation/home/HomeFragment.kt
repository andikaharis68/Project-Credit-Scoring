package com.andika.project_credit_scoring.presentation.home

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHomeBinding
import com.andika.project_credit_scoring.util.Constanst.ROLE
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import com.andika.project_credit_scoring.util.Constanst.USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_logout.view.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedViewModel : MainActivityViewModel
    var user = ""
    var role = ""

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        user = sharedPref.getString(USERNAME, "")!!
        role = sharedPref.getString(ROLE, "")!!
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding.apply {
            textInfoUser.text = user
            textInfoRole.text = "You're access as a $role"
            if (role == "MASTER") {
                navigationAccount.visibility = View.GONE
            }
            homeBtnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_global_loginFragment)
                sharedViewModel.hideBottomVav(true)
            }
            homeBtnTransaction.setOnClickListener {
                findNavController().navigate(R.id.action_global_transactionFragment)
                sharedViewModel.hideBottomVav(false)
            }
            homeBtnReport.setOnClickListener {
                findNavController().navigate(R.id.action_global_historyFragment)
                sharedViewModel.hideBottomVav(false)
            }
            homeBtnLogout.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_history, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
                dialogView.alertBtnCancel.setOnClickListener {
                    alertDialog.dismiss()
                }
                dialogView.alert_btn_exit.setOnClickListener {
                    sharedPref.edit()
                        .putString(TOKEN, "")
                        .putString(USERNAME, "")
                        .putString(ROLE, "")
                        .apply()
                    findNavController().navigate(R.id.action_global_loginFragment)
                    sharedViewModel.hideBottomVav(true)
                }
            }
            homeBtnAccount.setOnClickListener {
                findNavController().navigate(R.id.action_global_accountFragment)
                sharedViewModel.hideBottomVav(true)
            }
        }
        return binding.root
    }

    private fun initViewModel(){
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }
}