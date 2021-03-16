package com.andika.project_credit_scoring.presentation.account

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.account.Account
import com.andika.project_credit_scoring.account.AccountCoba
import com.andika.project_credit_scoring.databinding.CardViewAccountBinding

class AccountViewHolder(view: View, private val accountClickListener: AccountClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewAccountBinding.bind(view)

    fun bind(account: AccountCoba, select: String) {
        binding.apply {
            if (account.role == select) {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#FEC84D"))
            } else {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#e2e2e2"))
            }

            nameTv.text = account.name
            emailTv.text = account.email
            roleTv.text = account.role

            addAccount.setOnClickListener{
                Log.d("ADD", "Masuk")
            }
        }
    }
}