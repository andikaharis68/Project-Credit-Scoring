package com.andika.project_credit_scoring.presentation.home

import android.app.AlertDialog
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
import com.andika.project_credit_scoring.databinding.FragmentHomeMasterBinding
import com.andika.project_credit_scoring.util.Constanst
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_logout.view.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeMasterFragment : Fragment() {
    lateinit var binding : FragmentHomeMasterBinding
    lateinit var sharedViewModel: MainActivityViewModel

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = FragmentHomeMasterBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.apply {
            masterBtnAccount.setOnClickListener {
                findNavController().navigate(R.id.action_global_accountFragment)
            }
            masterBtnRoles.setOnClickListener {
                findNavController().navigate(R.id.action_global_rolesFragment)
            }
            masterBtnListRoles.setOnClickListener{
                findNavController().navigate(R.id.action_global_listRoleFragment)
            }
            masterBtnLogout.setOnClickListener {
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
                        .putString(Constanst.TOKEN, "")
                        .putString(Constanst.FULLNAME, "")
                        .putString(Constanst.ROLE, "")
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