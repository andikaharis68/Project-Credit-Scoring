package com.andika.project_credit_scoring.presentation.home

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHomeBinding
import com.andika.project_credit_scoring.util.Constanst.FULLNAME
import com.andika.project_credit_scoring.util.Constanst.ROLE
import com.andika.project_credit_scoring.util.Constanst.TOKEN
import com.andika.project_credit_scoring.util.Constanst.USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_logout.view.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedViewModel : MainActivityViewModel
    var user = ""
    var role = ""
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        user = sharedPref.getString(FULLNAME, "")!!
        role = sharedPref.getString(ROLE, "")!!
        binding = FragmentHomeBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.apply {
            textInfoUser.text = user
            textInfoRupiah.text = formatRupiah.format(1200000)
            textInfoRole.text = "You're access as a $role"
            highlightMenu.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_home, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
            }
            homeBtnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_global_profileFragment)
                sharedViewModel.hideBottomVav(false)
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
                val dialogView = LayoutInflater.from(requireContext()).inflate(
                    R.layout.alert_logout,
                    null
                )
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
        }
        return binding.root
    }

    private fun initViewModel(){
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }
}