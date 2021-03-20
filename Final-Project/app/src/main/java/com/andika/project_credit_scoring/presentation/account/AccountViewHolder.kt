package com.andika.project_credit_scoring.presentation.account

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.R
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.databinding.CardViewAccountBinding

class AccountViewHolder(view: View, private val accountClickListener: AccountClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewAccountBinding.bind(view)

    fun bind(account: Account, select: String) {
        binding.apply {
            if (account.data?.list. == select) {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#FEC84D"))
            } else {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#ffffff"))
            }

            if (account.role == "staff") {
                imageCard.setBackgroundResource(R.drawable.staff)
            } else {
                imageCard.setBackgroundResource(R.drawable.supervisor)
            }
            nameTv.text = account.name
            emailTv.text = account.email
            roleTv.text = account.role

        }
    }
}