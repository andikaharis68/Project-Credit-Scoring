package com.andika.project_credit_scoring.presentation.roles

import android.app.AlertDialog
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
import com.andika.project_credit_scoring.databinding.FragmentListRoleBinding
import com.andika.project_credit_scoring.databinding.FragmentRolesBinding
import com.andika.project_credit_scoring.model.roles.ListRole
import com.andika.project_credit_scoring.presentation.history.HistoryViewAdapter
import com.andika.project_credit_scoring.presentation.history.HistoryViewModel
import com.andika.project_credit_scoring.util.component.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_delete_account.*
import kotlinx.android.synthetic.main.alert_delete_account.view.*
import kotlinx.android.synthetic.main.fragment_list_role.*

@AndroidEntryPoint
class ListRoleFragment : Fragment() {

    lateinit var binding : FragmentListRoleBinding
    lateinit var viewModel : RoleViewModel
    lateinit var loadingDialog: androidx.appcompat.app.AlertDialog
    lateinit var rvAdapter: ListRoleAdapter
    var roleList = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
        getAllHistory()
        binding = FragmentListRoleBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("roleList", "$roleList")
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

    private fun subscribe() {
        viewModel.deleteLiveData.observe(this) { id ->
            val dialogView = LayoutInflater.from(requireContext()).inflate(
                R.layout.alert_delete_account,
                null
            )
            val dialogBuilder = AlertDialog.Builder(requireContext()).setView(dialogView)
            val alertDialog = dialogBuilder.show()
            dialogView.alert_text_delete.text = "Do you really want to delete this role?"
            dialogView.alert_btn_cancel_delete.setOnClickListener {
                alertDialog.dismiss()
            }
            dialogView.alert_btn_delete.setOnClickListener {
                deleteRole(id!!)
                alertDialog.dismiss()
            }
        }

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

    private fun deleteRole(id: String) =
        viewModel.deleteRole(id).observe(requireActivity()) {
            binding.apply {
                loadingDialog.show()
                when (it?.code) {
                    200 -> {
                        loadingDialog.hide()
                        Toast.makeText(
                            requireContext(),
                            "Success delete this role",
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
}