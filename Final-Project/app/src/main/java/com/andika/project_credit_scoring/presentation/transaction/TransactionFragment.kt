package com.andika.project_credit_scoring.presentation.transaction

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHistoryBinding
import com.andika.project_credit_scoring.databinding.FragmentTransactionBinding
import com.andika.project_credit_scoring.entity.RequestApproval
import com.andika.project_credit_scoring.presentation.history.HistoryViewAdapter
import com.andika.project_credit_scoring.presentation.history.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_approval_transaction.view.*
import kotlinx.android.synthetic.main.fragment_transaction.*

@AndroidEntryPoint
class TransactionFragment : Fragment() {


    lateinit var binding: FragmentTransactionBinding
    lateinit var viewModel: TransactionViewModel
    lateinit var rvAdapter: TransactionViewAdapter
    lateinit var requestApproveValue: RequestApproval

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTransactionBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            rvAdapter = TransactionViewAdapter(viewModel)
            recyclerViewTransaction.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }

            viewModel.getALlTransaction().observe(requireActivity()) {
                Log.d("IT", "${it!!.data}")
                it?.data?.list?.apply {
                    Log.d("THIS", "$this")
                    rvAdapter.setData(this)
                }
            }

        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
    }

    private fun subscribe(){
        viewModel.detailLiveData.observe(this){
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_approval_transaction, null)
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.dialog_name.text = it?.customer?.name
            dialogView.dialog_address.text = it?.customer?.address
            dialogView.dialog_email.text = it?.customer?.email
            dialogView.dialog_number_identity.text = it?.customer?.idNumber.toString()
            dialogView.dialog_employee_type.text = it?.customer?.employeeType
            dialogView.dialog_submitter.text = it?.submitter
            dialogView.dialog_income.text = "Rp. ${it?.income}"
            dialogView.dialog_outcome.text = "Rp. ${it?.outcome}"
            dialogView.dialog_loan.text = "Rp. ${it?.loan}"
            dialogView.dialog_interest.text = "Rp. ${it?.interest}"
            dialogView.dialog_credit_ratio.text = "${it?.creditRatio}%"
            dialogView.dialog_main_loan.text = "Rp. ${it?.mainLoan}"
            dialogView.dialog_interest_rate.text = "${it?.interestRate}%"
            dialogView.dialog_tenor.text = "${it?.tenor} Mount"
            dialogView.dialog_reason_type.text = it?.needType
            if(it?.employeeCriteria == true) {
                dialogView.dialog_employee_criteria.text = "Pass"
                dialogView.dialog_employee_criteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                dialogView.dialog_employee_criteria.text = "Not Pass"
                dialogView.dialog_employee_criteria.setTextColor(Color.parseColor("#ba0f30"))
            }
            if(it?.financeCriteria == true) {
                dialogView.dialog_financial_criteria.text = "Pass"
                dialogView.dialog_financial_criteria.setTextColor(Color.parseColor("#00B1B0"))
            } else {
                dialogView.dialog_financial_criteria.text = "Not Pass"
                dialogView.dialog_financial_criteria.setTextColor(Color.parseColor("#ba0f30"))
            }
            dialogView.dialog_note.text = it?.notes
            dialogView.dialog_btn_reject.setOnClickListener { itReject ->
                requestApproveValue = RequestApproval(false, it?.id)
                viewModel.approveTransaction(requestApproveValue)
                alertDialog.dismiss()
            }
            dialogView.dialog_btn_approve.setOnClickListener { itReject ->
                requestApproveValue = RequestApproval(true, it?.id)
                viewModel.approveTransaction(requestApproveValue)
                alertDialog.dismiss()
            }
        }
    }
}