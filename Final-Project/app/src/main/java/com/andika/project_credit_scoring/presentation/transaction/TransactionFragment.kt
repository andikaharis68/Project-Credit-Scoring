package com.andika.project_credit_scoring.presentation.transaction

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.FragmentHistoryBinding
import com.andika.project_credit_scoring.databinding.FragmentTransactionBinding
import kotlinx.android.synthetic.main.fragment_transaction.*


class TransactionFragment : Fragment() {

    lateinit var binding: FragmentTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.apply {

        }
        return binding.root
    }
}