package com.andika.project_credit_scoring.presentation.account

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andika.project_credit_scoring.databinding.CardViewAccountBinding
import com.andika.project_credit_scoring.model.account.ListAccount
import com.andika.project_credit_scoring.util.Constanst.MASTER
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*

class AccountViewHolder(view: View, private val accountClickListener: AccountClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewAccountBinding.bind(view)
    private val glide = Glide.with(view)
    fun bind(account: ListAccount?) {
        Log.d("HOLDER", "$account")
        binding.apply {
            if (account?.isVerified == false){
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#d94c67"))
                cardTextApproval.visibility = View.GONE
            } else {
                cardItemAccount.setCardBackgroundColor(Color.parseColor("#ffffff"))
            }

            if (account?.role == MASTER) {
                cardBtnDelete.visibility = View.GONE
            } else {
                cardBtnDelete.visibility = View.VISIBLE
            }

            cardBtnDelete.setOnClickListener{
                accountClickListener.onDelete(account?.id!!)
            }
            glide.load(account?.profilePicture).into(imageCard)
            nameTv.text = account?.username
            fullnameTv.text = account?.fullName
            emailTv.text = account?.email
            roleTv.text = account?.role
        }
    }
}