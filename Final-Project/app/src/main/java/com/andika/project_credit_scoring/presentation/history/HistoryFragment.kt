package com.andika.project_credit_scoring.presentation.history

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHistoryBinding
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.presentation.account.AccountViewModel
import com.andika.project_credit_scoring.repositories.HistoryRepositoryImpl
import com.andika.project_credit_scoring.util.ResourceStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_account.view.*
import kotlinx.android.synthetic.main.dialog_history.*
import kotlinx.android.synthetic.main.dialog_history.view.*
import kotlinx.android.synthetic.main.dialog_loading.*

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: HistoryViewModel
    lateinit var rvAdapter: HistoryViewAdapter
    private var trans_employee = 0
    private var trans_non_employee = 0
    private var trans_contract_employee = 0
    private var progress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.apply {
            rvAdapter = HistoryViewAdapter(viewModel)
            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }

            viewModel.getALlHistory().observe(requireActivity()) {
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
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.detailLiveData.observe(this){
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_history, null)
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.dialog_btn_close.setOnClickListener{
                alertDialog.dismiss()
            }
            dialogView.dialog_text_name.text = it?.approval?.transaction?.customer?.name
            dialogView.dialog_text_email.text = it?.approval?.transaction?.customer?.email
            dialogView.dialog_text_address.text = it?.approval?.transaction?.customer?.address
            dialogView.dialog_text_status_employee.text = it?.approval?.transaction?.customer?.employeeType
            dialogView.dialog_text_identity.text = it?.approval?.transaction?.customer?.idNumber.toString()
            dialogView.dialog_text_income.text = "Rp. ${it?.approval?.transaction?.income}"
            dialogView.dialog_text_outcome.text = "Rp. ${it?.approval?.transaction?.outcome}"
            dialogView.dialog_text_loan.text = "Rp. ${it?.approval?.transaction?.loan}"
            progress = it?.approval?.transaction?.creditRatio?.toInt()!!
            dialogView.progress_bar.progress = progress.toFloat()
            dialogView.dialog_text_interest_rate.text = "${it?.approval?.transaction?.interestRate}%"
            dialogView.dialog_text_credit_ratio.text = "${it?.approval?.transaction?.creditRatio}%"
            dialogView.dialog_text_installment.text = it?.approval?.transaction?.installment?.toString()
            dialogView.dialog_text_total_installment.text = it?.approval?.transaction?.installmentTotal?.toString()
            dialogView.dialog_text_tenor.text = "${it?.approval?.transaction?.tenor} mount"
            if (it?.approval?.transaction?.financeCriteria == true){
                dialogView.dialog_text_financial_criteria.text = "Pass"
            } else {
                dialogView.dialog_text_financial_criteria.text = "Not Pass"
            }
            if (it?.approval?.transaction?.employeeCriteria == true){
                dialogView.dialog_text_employee_criteria.text = "Pass"
            } else {
                dialogView.dialog_text_employee_criteria.text = "Not Pass"
            }
            dialogView.dialog_text_date_submited.text = it?.submitDate
            dialogView.dialog_text_date_approved.text = it?.approvalDate
            if (it?.approval?.approve == true){
                dialogView.dialog_image_approval.setBackgroundResource(R.drawable.approved)
            } else {
                dialogView.dialog_image_approval.setBackgroundResource(R.drawable.rejected)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}