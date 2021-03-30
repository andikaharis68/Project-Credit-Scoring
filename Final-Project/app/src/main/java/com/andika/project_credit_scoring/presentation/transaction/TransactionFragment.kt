package com.andika.project_credit_scoring.presentation.transaction

import android.app.AlertDialog
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.MainActivityViewModel
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentTransactionBinding
import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.util.Constanst
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_approval_transaction.view.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    lateinit var binding: FragmentTransactionBinding
    lateinit var viewModel: TransactionViewModel
    lateinit var sharedViewModel: MainActivityViewModel
    lateinit var rvAdapter: TransactionViewAdapter
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    lateinit var requestApproveValue: RequestApproval
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    var approvalTransaction = ""

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        approvalTransaction = sharedPref.getString(Constanst.APPROVAL_TRANSACTION, "")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = LoadingDialog.build(requireContext())
        binding.apply {
            rvAdapter = TransactionViewAdapter(viewModel)
            recyclerViewTransaction.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
            viewModel.getALlTransaction().observe(requireActivity()) {
                it?.data?.list?.apply {
                    rvAdapter.setData(this, approvalTransaction)
                }
            }
            refreshTransaction.setOnRefreshListener {
                viewModel.getALlTransaction()
            }
            btnBack.setOnClickListener{
                findNavController().navigate(R.id.action_transactionFragment_to_homeFragment)
                sharedViewModel.hideBottomVav(true)
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun subscribe(){
        viewModel.detailLiveData.observe(this){
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_approval_transaction, null)
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.apply {
                dialog_name.text = it?.transaction?.customer?.name
                dialog_address.text = it?.transaction?.customer?.address
                dialog_email.text = it?.transaction?.customer?.email
                dialog_number_identity.text = it?.transaction?.customer?.idNumber.toString()
                dialog_employee_type.text = it?.transaction?.customer?.employeeType
                dialog_submitter.text = it?.transaction?.submitter
                dialog_income.text = formatRupiah.format(it?.transaction?.income)
                dialog_outcome.text = formatRupiah.format(it?.transaction?.outcome)
                dialog_loan.text = formatRupiah.format(it?.transaction?.loan)
                dialog_interest.text = formatRupiah.format(it?.transaction?.interest)
                dialog_credit_ratio.text = "${it?.transaction?.creditRatio}%"
                dialog_main_loan.text = formatRupiah.format(it?.transaction?.mainLoan)
                dialog_interest_rate.text = "${it?.transaction?.interestRate}%"
                dialog_tenor.text = "${it?.transaction?.tenor} month"
                dialog_reason_type.text = it?.transaction?.needType.toString()
                if(it?.transaction?.employeeCriteria == true) {
                    dialog_employee_criteria.text = "Pass"
                    dialog_employee_criteria.setTextColor(Color.parseColor("#00B1B0"))
                } else {
                    dialog_employee_criteria.text = "Not Pass"
                    dialog_employee_criteria.setTextColor(Color.parseColor("#ba0f30"))
                }
                if(it?.transaction?.financeCriteria == true) {
                    dialog_financial_criteria.text = "Pass"
                    dialog_financial_criteria.setTextColor(Color.parseColor("#00B1B0"))
                } else {
                    dialog_financial_criteria.text = "Not Pass"
                    dialog_financial_criteria.setTextColor(Color.parseColor("#ba0f30"))
                }
                dialog_note.text = it?.transaction?.notes
                dialog_btn_reject.setOnClickListener { itReject ->
                    requestApproveValue = RequestApproval(false)
                    aprrovalTransaction(it?.id.toString() , requestApproveValue)
                    alertDialog.dismiss()
                }
                dialog_btn_approve.setOnClickListener { itReject ->
                    requestApproveValue = RequestApproval(true)
                    aprrovalTransaction(it?.id.toString(), requestApproveValue)
                    alertDialog.dismiss()
                }
            }
        }
    }
    fun aprrovalTransaction(id : String, requestApproval: RequestApproval) =
        viewModel.approveTransaction(id, requestApproval).observe(this){
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        "Success transaction from ${it.data?.transaction?.customer?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
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
