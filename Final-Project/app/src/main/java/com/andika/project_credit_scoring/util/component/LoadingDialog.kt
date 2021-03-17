package com.andika.project_credit_scoring.util.component

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import com.andika.project_credit_scoring.R

class LoadingDialog {
    companion object {
        fun build(context :Context): AlertDialog {
            val inflate = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null)
            val dialog = AlertDialog.Builder(context).setView(inflate).setCancelable(true).create()
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            return dialog
        }
    }
}