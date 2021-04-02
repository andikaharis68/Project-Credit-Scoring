package com.andika.project_credit_scoring.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.model.history.ResponseTotal
import com.andika.project_credit_scoring.presentation.history.HistoryClickListener
import com.andika.project_credit_scoring.repositories.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HistoryRepository) : ViewModel() {
    fun getTotal() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseTotal? = null
                try {
                    response = repository.getTotal()
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        ResponseTotal(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }
}