package com.andika.project_credit_scoring.presentation.account

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.databinding.FragmentAccountBinding
import com.andika.project_credit_scoring.util.ResourceStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_account.view.*

@AndroidEntryPoint
class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    lateinit var viewModel: AccountViewModel
    lateinit var rvAdapter: AccountViewAdapter
    var selected : String = ""
    private var accountRequestValue: Account? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAccountBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        viewModel.getALlAccount()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            rvAdapter = AccountViewAdapter(viewModel)
            recyclerViewItem.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
            imageStaff.setOnClickListener{
                textAccount.text = "Staff Account"
                selected = "staff"
                viewModel.getALlAccount()
            }
            imageSpv.setOnClickListener{
                textAccount.text = "Supervisor Account"
                selected = "supervisor"
                viewModel.getALlAccount()
            }
            addAcccount.setOnClickListener{
                val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_account, null)
                val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
                val alertDialog = dialogBuilder.show()
                dialogView.dialog_btn_create.setOnClickListener {
                    accountRequestValue = Account(
                        name = dialogView.dialog_et_name.text.toString(),
                        email = dialogView.dialog_et_email.text.toString(),
                        password = dialogView.dialog_et_password.text.toString(),
                        role = dialogView.dialog_et_role.text.toString()
                    )
                    viewModel.addAccount(accountRequestValue!!)
                    viewModel.getALlAccount()
                    alertDialog.dismiss()
                }
                dialogView.dialogBtnCancel.setOnClickListener {
                    alertDialog.dismiss()
                }
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.accountsLiveData.observe(this){
            when (it.status) {
                ResourceStatus.LOADING -> Log.d("APP", "Loading..")
                ResourceStatus.SUCCESS -> {
                    val data: List<Account> = it.data as List<Account>
                    rvAdapter.setData(data,selected)
                }
            }
        }
    }
}