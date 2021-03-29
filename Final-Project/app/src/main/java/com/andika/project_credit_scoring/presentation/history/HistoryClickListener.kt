package com.andika.project_credit_scoring.presentation.history

import com.andika.project_credit_scoring.model.history.ListHistory

interface HistoryClickListener {
    fun onDetail(history: ListHistory?)
}