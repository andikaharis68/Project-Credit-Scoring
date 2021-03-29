package com.andika.project_credit_scoring.presentation.roles

import android.os.Bundle
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
import com.andika.project_credit_scoring.databinding.FragmentListRoleBinding
import com.andika.project_credit_scoring.databinding.FragmentRolesBinding
import com.andika.project_credit_scoring.presentation.history.HistoryViewAdapter
import com.andika.project_credit_scoring.presentation.history.HistoryViewModel
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_role.*

@AndroidEntryPoint
class ListRoleFragment : Fragment() {

    lateinit var binding : FragmentListRoleBinding
    lateinit var viewModel : RoleViewModel
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    lateinit var rvAdapter: ListRoleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        getAllHistory()
        binding = FragmentListRoleBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {
            loadingDialog = LoadingDialog.build(requireContext())
            rvAdapter = ListRoleAdapter(viewModel)
            recyclerViewRole.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_listRoleFragment_to_homeMasterFragment)
            }
        }
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RoleViewModel::class.java)
    }

    fun getAllHistory() =
        viewModel.getALlRole().observe(requireActivity()) {
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

}