package com.andika.project_credit_scoring.presentation.transaction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.entity.*
import com.andika.project_credit_scoring.repositories.HistoryRepository
import com.andika.project_credit_scoring.repositories.TransactionRepository
import com.andika.project_credit_scoring.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository: TransactionRepository) : ViewModel(), TransactionClickListener{

    private var _detailLiveData = MutableLiveData<ListTransaction?>()

    val detailLiveData: MutableLiveData<ListTransaction?>
        get() {
            return _detailLiveData
        }

    fun getALlTransaction() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: Transaction? = null
                try {
                    response = repository.getTransaction()
                    Log.d("RESPONSE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        Transaction(
                            code = 400,
                            data = null,
                            message = "Email or Password invalid!",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    override fun onDetail(transaction: ListTransaction?) {
        _detailLiveData.postValue(transaction)
    }

    fun approveTransaction(requestApproval: RequestApproval) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.approvalTransaction(requestApproval)
            Log.d("APPROVE", "$requestApproval")
        }
    }
}