package com.andika.project_credit_scoring.presentation.history

import android.app.AlertDialog
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
import com.andika.project_credit_scoring.databinding.FragmentHistoryBinding
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_history.view.*
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: HistoryViewModel
    lateinit var sharedViewModel: MainActivityViewModel
    lateinit var rvAdapter: HistoryViewAdapter
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    private var progress = 0
    val localeID = Locale("in", "ID")
    val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        getAllHistory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = LoadingDialog.build(requireContext())
        binding.apply {
            rvAdapter = HistoryViewAdapter(viewModel)
            recyclerViewHistory.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }

            btnBack.setOnClickListener{
                findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
                sharedViewModel.hideBottomVav(true)
            }
            textApprove.setOnClickListener{
                getRejectedHistory()
                textApprove.setBackgroundResource(R.drawable.red_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
                textReject.setBackgroundResource(R.drawable.white_roundshape)
                textApprove.setTextColor(Color.parseColor("#ffffff"))
                textReject.setTextColor(Color.parseColor("#000000"))
                textAll.setTextColor(Color.parseColor("#000000"))
            }
            textAll.setOnClickListener{
                getAllHistory()
                textAll.setBackgroundResource(R.drawable.red_roundshape)
                textApprove.setBackgroundResource(R.drawable.white_roundshape)
                textReject.setBackgroundResource(R.drawable.white_roundshape)
                textApprove.setTextColor(Color.parseColor("#000000"))
                textReject.setTextColor(Color.parseColor("#000000"))
                textAll.setTextColor(Color.parseColor("#ffffff"))
            }
            textReject.setOnClickListener {
                getApprovedHistory()
                textReject.setBackgroundResource(R.drawable.red_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
                textApprove.setBackgroundResource(R.drawable.white_roundshape)
                textApprove.setTextColor(Color.parseColor("#000000"))
                textReject.setTextColor(Color.parseColor("#ffffff"))
                textAll.setTextColor(Color.parseColor("#000000"))
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.detailLiveData.observe(this){
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_history, null)
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.apply {
                dialog_btn_close.setOnClickListener{
                    alertDialog.dismiss()
                }
                dialog_text_name.text = it?.approval?.transaction?.customer?.name
                dialog_text_email.text = it?.approval?.transaction?.customer?.email
                dialog_text_address.text = it?.approval?.transaction?.customer?.address
                dialog_text_status_employee.text = it?.approval?.transaction?.customer?.employeeType
                dialog_text_identity.text = it?.approval?.transaction?.customer?.idNumber.toString()
                dialog_text_income.text = formatRupiah.format(it?.approval?.transaction?.income)
                dialog_text_outcome.text = formatRupiah.format(it?.approval?.transaction?.outcome)
                dialog_text_loan.text = formatRupiah.format(it?.approval?.transaction?.loan)
                dialog_text_installment.text = formatRupiah.format(it?.approval?.transaction?.installment?.toInt())
                dialog_text_total_installment.text = formatRupiah.format(it?.approval?.transaction?.installmentTotal?.toInt())
                progress = it?.approval?.transaction?.creditRatio?.toInt()!!
                progress_bar.progress = progress.toFloat()
                dialog_text_interest_rate.text = "${it?.approval?.transaction?.interestRate}%"
                dialog_text_credit_ratio.text = "${it?.approval?.transaction?.creditRatio}%"
                dialog_text_tenor.text = "${it?.approval?.transaction?.tenor} month"
                dialog_history_text_submitter.text = it?.approval?.transaction?.submitter
                if (it?.approval?.transaction?.financeCriteria == true){
                    dialog_text_financial_criteria.text = "Pass"
                } else {
                    dialog_text_financial_criteria.text = "Not Pass"
                }
                if (it?.approval?.transaction?.employeeCriteria == true){
                    dialog_text_employee_criteria.text = "Pass"
                } else {
                    dialog_text_employee_criteria.text = "Not Pass"
                }
                dialog_text_date_submited.text = it?.submitDate
                dialog_text_date_approved.text = it?.approvalDate
                if (it?.approval?.approve == true){
                    dialog_image_approval.setBackgroundResource(R.drawable.approved)
                } else {
                    dialog_image_approval.setBackgroundResource(R.drawable.rejected)
                }
            }
        }
    }

    fun getAllHistory() =
        viewModel.getALlHistory().observe(requireActivity()) {
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    it?.data?.list?.apply {
                        rvAdapter.setData(this)
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

    fun getApprovedHistory() =
        viewModel.getApprovedHistory().observe(requireActivity()) {
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    it?.data?.list?.apply {
                        rvAdapter.setData(this)
                        Log.d("APPROVED", "$this")
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

    fun getRejectedHistory() =
        viewModel.getRejectedHistory().observe(requireActivity()) {
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    it?.data?.list?.apply {
                        rvAdapter.setData(this)
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

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}