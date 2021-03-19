package com.andika.project_credit_scoring.presentation.history

import com.andika.project_credit_scoring.entity.ListItem

interface HistoryClickListener {
    fun onDetail(history: ListItem?)
}