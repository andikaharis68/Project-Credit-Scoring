package com.andika.project_credit_scoring.presentation.account


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.databinding.FragmentAccountBinding
import com.andika.project_credit_scoring.util.ResourceStatus
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_add_account.view.*

@AndroidEntryPoint
class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    lateinit var viewModel: AccountViewModel
    lateinit var rvAdapter: AccountViewAdapter
    lateinit var loadingDialog: AlertDialog
    var selected : String = ""
    private var accountRequestValue: Account? = null

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
        binding.apply {
            rvAdapter = AccountViewAdapter(viewModel)
            recyclerViewAccount.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }

            viewModel.getALlAccount().observe(requireActivity()) {
                Log.d("IT", "${it!!.data}")
                it?.data?.list?.apply {
                    Log.d("THIS", "$this")
                    rvAdapter.setData(this)
                }
            }

            textStaff.setOnClickListener{
                selected = "Staff"
                viewModel.getALlAccount()
                textStaff.setBackgroundResource(R.drawable.red_roundshape)
                textSupervisor.setBackgroundResource(R.drawable.white_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
            }
            textSupervisor.setOnClickListener{
                selected = "Supervisor"
                viewModel.getALlAccount()
                textSupervisor.setBackgroundResource(R.drawable.red_roundshape)
                textStaff.setBackgroundResource(R.drawable.white_roundshape)
                textAll.setBackgroundResource(R.drawable.white_roundshape)
            }
            textAll.setOnClickListener {
                selected = "All"
                viewModel.getALlAccount()
                textAll.setBackgroundResource(R.drawable.red_roundshape)
                textSupervisor.setBackgroundResource(R.drawable.white_roundshape)
                textStaff.setBackgroundResource(R.drawable.white_roundshape)
            }
            addAccount.setOnClickListener{
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
                    loadingDialog.hide()
                    val data: List<Account> = it.data as List<Account>
                    rvAdapter.setData(data,selected)
                }
                ResourceStatus.FAIL -> {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        viewModel.accountLiveData.observe(this){
            when (it.status) {
                ResourceStatus.LOADING -> Log.d("APP", "Loading..")
                ResourceStatus.SUCCESS -> {
                    val data: List<Account> = it.data as List<Account>
                    rvAdapter.setData(data,selected)
                }
                ResourceStatus.FAIL -> {
                    loadingDialog.hide()
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}