package com.andika.project_credit_scoring.presentation.account

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentAccountBinding
import com.andika.project_credit_scoring.presentation.login.LoginViewModel
import com.andika.project_credit_scoring.repositories.AccountRepository
import com.andika.project_credit_scoring.repositories.AccountRepositoryImpl

class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding
    lateinit var viewModel: AccountViewModel
    lateinit var rvAdapter: AccountViewAdapter
    var selected : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
        viewModel.getALlAccount()
        binding = FragmentAccountBinding.inflate(layoutInflater)
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
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = AccountRepositoryImpl()
                return AccountViewModel(repo) as T
            }
        }).get(AccountViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.accountsLiveData.observe(this){
            rvAdapter.setData(it, selected)
        }
    }
}