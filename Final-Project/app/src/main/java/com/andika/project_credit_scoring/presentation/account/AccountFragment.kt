package com.andika.project_credit_scoring.presentation.account


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentAccountBinding
import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_delete_account.view.*
import kotlinx.android.synthetic.main.alert_logout.view.*
import kotlinx.android.synthetic.main.card_view_account.*
import kotlinx.android.synthetic.main.dialog_add_account.*
import kotlinx.android.synthetic.main.dialog_add_account.view.*
import kotlinx.android.synthetic.main.fragment_account.*

@AndroidEntryPoint
class AccountFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentAccountBinding
    lateinit var viewModel: AccountViewModel
    lateinit var rvAdapter: AccountViewAdapter
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    private lateinit var accountRequestValue: RequestAddAccount
    private var role: String = "STAFF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAccountBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog.build(requireContext())
        initViewModel()
        subscribe()
        viewModel.getALlAccount()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = LoadingDialog.build(requireContext())
        binding.apply {
            rvAdapter = AccountViewAdapter(viewModel)
            recyclerViewAccount.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
            getAllAccount()

            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_homeMasterFragment)
            }

            textVerified.setOnClickListener {
                viewModel.getALlAccount()
                textVerified.setBackgroundResource(R.drawable.red_roundshape)
                textNotVerified.setBackgroundResource(R.drawable.white_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
                textVerified.setTextColor(Color.parseColor("#ffffff"))
                textAll.setTextColor(Color.parseColor("#000000"))
                textNotVerified.setTextColor(Color.parseColor("#000000"))
            }
            textNotVerified.setOnClickListener {
                viewModel.getALlAccount()
                textNotVerified.setBackgroundResource(R.drawable.red_roundshape)
                textVerified.setBackgroundResource(R.drawable.white_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
                textVerified.setTextColor(Color.parseColor("#000000"))
                textAll.setTextColor(Color.parseColor("#000000"))
                textNotVerified.setTextColor(Color.parseColor("#ffffff"))
            }
            textAll.setOnClickListener {
                viewModel.getALlAccount()
                textAll.setBackgroundResource(R.drawable.red_roundshape)
                textNotVerified.setBackgroundResource(R.drawable.white_roundshape)
                textVerified.setBackgroundResource(R.drawable.white_roundshape)
                textVerified.setTextColor(Color.parseColor("#000000"))
                textAll.setTextColor(Color.parseColor("#ffffff"))
                textNotVerified.setTextColor(Color.parseColor("#000000"))
            }

            btnAddAccount.setOnClickListener {
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_account, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
                dialogView.dialog_btn_create.setOnClickListener {
                    accountRequestValue = RequestAddAccount(
                        fullName = dialogView.dialog_et_name.text.toString(),
                        email = dialogView.dialog_et_email.text.toString(),
                        profilePicture = "",
                        username = dialogView.dialog_et_username.text.toString(),
                        role = role
                    )
                    addAccount(accountRequestValue)
                    alertDialog.dismiss()
                }
                dialogView.dialogBtnCancel.setOnClickListener {
                    alertDialog.dismiss()
                }

                val adapter = ArrayAdapter.createFromResource(
                    requireContext(),
                    R.array.role,
                    android.R.layout.simple_spinner_dropdown_item
                )

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                dialogView.spinner_role.adapter = adapter
                dialogView.spinner_role.onItemSelectedListener = this@AccountFragment
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.deleteLiveData.observe(this) { id ->
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.alert_delete_account, null)
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.alert_btn_cancel_delete.setOnClickListener {
                alertDialog.dismiss()
            }
            dialogView.alert_btn_delete.setOnClickListener {
                deleteAccount(id!!)
                alertDialog.dismiss()
            }
        }

    }

    private fun getAllAccount() =
        viewModel.getALlAccount().observe(requireActivity()) {
            loadingDialog.show()
            when (it?.code) {
                200 -> {
                    loadingDialog.hide()
                    it?.data?.list?.apply {
                        Log.d("THIS", "$this")
                        rvAdapter.setData(this)
                    }
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

    private fun addAccount(requestAddAccount: RequestAddAccount) =
        viewModel.addAccount(requestAddAccount).observe(requireActivity()) {
            binding.apply {
                loadingDialog.show()
                when (it?.code) {
                    200 -> {
                        loadingDialog.hide()
                        Toast.makeText(
                            requireContext(),
                            "Success add this account",
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

    private fun deleteAccount(id: String) =
        viewModel.deleteAccount(id).observe(requireActivity()) {
            binding.apply {
                loadingDialog.show()
                when (it?.code) {
                    200 -> {
                        loadingDialog.hide()
                        Toast.makeText(
                            requireContext(),
                            "Success delete this account",
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


    //spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        role = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}





