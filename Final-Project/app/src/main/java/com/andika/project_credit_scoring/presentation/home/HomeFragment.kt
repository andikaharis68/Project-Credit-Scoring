package com.andika.project_credit_scoring.presentation.home

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHomeBinding
import com.andika.project_credit_scoring.presentation.history.HistoryViewModel
import com.andika.project_credit_scoring.util.Constanst.APPROVAL_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.FULLNAME
import com.andika.project_credit_scoring.util.Constanst.READ_ALL_REPORT
import com.andika.project_credit_scoring.util.Constanst.READ_ALL_TRANSACTION
import com.andika.project_credit_scoring.util.Constanst.ROLE
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import com.andika.project_credit_scoring.util.Constanst.USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_logout.view.*
import kotlinx.android.synthetic.main.dialog_home.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var sharedViewModel : MainActivityViewModel
    var user = ""
    var role = ""
    var readTransaction = ""
    var approvalTransaction = ""
    var readReport = ""

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        user = sharedPref.getString(FULLNAME, "")!!
        role = sharedPref.getString(ROLE, "")!!
        readTransaction = sharedPref.getString(READ_ALL_TRANSACTION, "")!!
        approvalTransaction = sharedPref.getString(APPROVAL_TRANSACTION, "")!!
        readReport = sharedPref.getString(READ_ALL_REPORT, "")!!
        if (readTransaction == "true"){
            Log.d("TRUE", "masuk")
        }
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.apply {
            textInfoUser.text = user
            textInfoRole.text = "You're access as a $role"
            if (approvalTransaction == "true" || readTransaction == "true"){
                homeBtnTransaction.setBackgroundResource(R.drawable.round_corner_white)
                homeBtnTransaction.setOnClickListener {
                    findNavController().navigate(R.id.action_global_transactionFragment)
                    sharedViewModel.hideBottomVav(false)
                }
            } else {
                homeBtnTransaction.setBackgroundResource(R.drawable.round_corner_grey2)
            }
            if (readReport == "true") {
                homeBtnReport.setBackgroundResource(R.drawable.round_corner_white)
                homeBtnReport.setOnClickListener {
                    findNavController().navigate(R.id.action_global_historyFragment)
                    sharedViewModel.hideBottomVav(false)
                }
            } else {
                homeBtnReport.setBackgroundResource(R.drawable.round_corner_grey2)
            }
            highlightMenu.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_home, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
            }
            homeBtnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_global_profileFragment)
                sharedViewModel.hideBottomVav(true)
            }

            homeBtnLogout.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.alert_logout, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
                dialogView.alertBtnCancel.setOnClickListener {
                    alertDialog.dismiss()
                }
                dialogView.alert_btn_exit.setOnClickListener {
                    sharedPref.edit()
                        .putString(TOKEN, "")
                        .putString(FULLNAME, "")
                        .putString(ROLE, "")
                        .apply()
                    findNavController().navigate(R.id.action_global_loginFragment)
                    sharedViewModel.hideBottomVav(true)
                    alertDialog.dismiss()
                }
            }
            viewModel.getTotal().observe(requireActivity()) {
                home_highlight_transaction.text = "${it?.data?.totalTransaction} total transaction"
                homeHighlightCustomer.text = "${it?.data?.totalCustomer} total customer"
            }
            highlightMenu.setOnClickListener {
                getTotal()
            }
        }
        return binding.root
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    fun getTotal() =
        viewModel.getTotal().observe(requireActivity()) {
            when (it?.code) {
                200 -> {
                    val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_home, null)
                    val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                    val alertDialog = dialogBuilder.show()
                    dialogView.dialog_home_transaction.text = it?.data?.totalTransaction.toString()
                    dialogView.dialog_home_approved.text = it?.data?.totalApproved.toString()
                    dialogView.dialog_home_rejected.text = it?.data?.totalRejected.toString()
                    dialogView.dialog_home_customer.text = it?.data?.totalCustomer.toString()
                }
                100 -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        requireContext(),
                        "${it?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}