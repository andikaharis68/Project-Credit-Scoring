package com.andika.project_credit_scoring.presentation.roles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentRolesBinding
import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RolesFragment : Fragment() {

    lateinit var binding : FragmentRolesBinding
    lateinit var viewModel: RoleViewModel
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    private lateinit var roleRequestValue: RequestRole

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRolesBinding.inflate(layoutInflater)
        initViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var isClickAddCustomer = false
        var isClickReadCustomer = false
        var isClickAddTransaction = false
        var isClickReadTransaction = false
        var isClickApprovalTransaction = false
        var isClickReadAllReport = false
        var isClickReadAllReportTransaction = false
        loadingDialog = LoadingDialog.build(requireContext())
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_rolesFragment_to_homeMasterFragment)
            }
            roleRbCustomerInput.setOnClickListener {
                if(!isClickAddCustomer){
                    roleRbCustomerInput.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbCustomerInput.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickAddCustomer = !isClickAddCustomer
            }
            roleRbCustomerRead.setOnClickListener {
                if(!isClickReadCustomer){
                    roleRbCustomerRead.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbCustomerRead.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickReadCustomer = !isClickReadCustomer
            }
            roleRbTransactionInput.setOnClickListener {
                if(!isClickAddTransaction){
                    roleRbTransactionInput.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbTransactionInput.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickAddTransaction = !isClickAddTransaction
            }
            roleRbTransactionRead.setOnClickListener {
                if(!isClickReadTransaction){
                    roleRbTransactionRead.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbTransactionRead.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickReadTransaction = !isClickReadTransaction
            }
            roleRbTransactionApproval.setOnClickListener {
                if(!isClickApprovalTransaction){
                    roleRbTransactionApproval.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbTransactionApproval.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickApprovalTransaction = !isClickApprovalTransaction
            }
            roleRbReadReport.setOnClickListener {
                if(!isClickReadAllReport){
                    roleRbReadReport.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbReadReport.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickReadAllReport = !isClickReadAllReport
            }
            roleRbReadReportTransaction.setOnClickListener {
                if(!isClickReadAllReportTransaction){
                    roleRbReadReportTransaction.setBackgroundResource(R.drawable.ic_check)
                } else {
                    roleRbReadReportTransaction.setBackgroundResource(R.drawable.stroke_white_blue)
                }
                isClickReadAllReportTransaction = !isClickReadAllReportTransaction
            }
            roleBtnSave.setOnClickListener {
                if (roleEtRoleName.text.isEmpty()){
                    Toast.makeText(
                        requireContext(),
                        "Please input name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (!isClickAddCustomer && !isClickReadCustomer && !isClickAddTransaction && !isClickReadTransaction &&
                            !isClickApprovalTransaction && !isClickReadAllReport && !isClickReadAllReportTransaction){
                    Toast.makeText(
                        requireContext(),
                        "Please checklist role",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    roleRequestValue = RequestRole(
                        name = roleEtRoleName.text.toString(),
                        inputCustomer = isClickAddCustomer,
                        readAllCustomer = isClickReadCustomer,
                        inputTransaction = isClickAddTransaction,
                        readAllTransaction = isClickReadTransaction,
                        approveTransaction = isClickApprovalTransaction,
                        readAllReport = isClickReadAllReport,
                        readAllReportByTransaction = isClickReadAllReportTransaction
                    )
                    addRole(roleRequestValue)
                }
            }
        }
        return binding.root
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
    }

    fun addRole(requestRole: RequestRole) =
        viewModel.addRole(requestRole).observe(requireActivity()) {
            Log.d("ROLES", "$requestRole")
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        "Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_rolesFragment_to_listRoleFragment)
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
}