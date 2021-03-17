package com.andika.project_credit_scoring.presentation.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andika.project_credit_scoring.databinding.FragmentHistoryBinding
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.presentation.account.AccountViewModel
import com.andika.project_credit_scoring.repositories.HistoryRepositoryImpl
import com.andika.project_credit_scoring.util.ResourceStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: HistoryViewModel
    lateinit var rvAdapter: HistoryViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        initViewModel()
        subscribe()
        viewModel.getALlHistory()
    }

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
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.historiesLiveData.observe(this){
            when (it.status) {
                ResourceStatus.LOADING -> Log.d("APP", "Loading..")
                ResourceStatus.SUCCESS -> {
                    val data: List<History> = it.data as List<History>
                    rvAdapter.setData(data)
                }
            }
        }
    }
}