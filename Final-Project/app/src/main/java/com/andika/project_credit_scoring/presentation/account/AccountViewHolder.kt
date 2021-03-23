package com.andika.project_credit_scoring.presentation.account

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.databinding.CardViewAccountBinding
import com.andika.project_credit_scoring.entity.ListAccount
import kotlinx.android.synthetic.main.alert_delete_account.view.*
import kotlinx.android.synthetic.main.card_view_account.*

class AccountViewHolder(view: View, private val accountClickListener: AccountClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewAccountBinding.bind(view)

    fun bind(account: ListAccount?) {
        Log.d("HOLDER", "$account")
        binding.apply {
            if (account?.isVerified == false){
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#d94c67"))
                cardTextApproval.visibility = View.GONE
            } else {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#ffffff"))
            }

            cardBtnDelete.setOnClickListener{
                accountClickListener.onDelete(account?.id!!)
            }

            nameTv.text = account?.username
            emailTv.text = account?.email
            roleTv.text = account?.roles
        }
    }
}