package com.andika.project_credit_scoring.presentation.transaction

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.model.*
import com.andika.project_credit_scoring.model.transaction.ListTransaction
import com.andika.project_credit_scoring.model.transaction.RequestApproval
import com.andika.project_credit_scoring.model.transaction.ResponseApproval
import com.andika.project_credit_scoring.model.transaction.ResponseTransaction
import com.andika.project_credit_scoring.repositories.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(private val repository: TransactionRepository) :
    ViewModel(), TransactionClickListener {

    private var _detailLiveData = MutableLiveData<ListTransaction?>()

    val detailLiveData: MutableLiveData<ListTransaction?>
        get() {
            return _detailLiveData
        }

    fun getALlTransaction() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseTransaction? = null
                try {
                    response = repository.getTransaction()
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseTransaction(
                            code = 100,
                            data = null,
                            message = "Failed connect to server",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    override fun onDetail(transaction: ListTransaction?) {
        _detailLiveData.postValue(transaction)
    }

    fun approveTransaction(id: String, requestApproval: RequestApproval) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseApproval? = null
                try {
                    response = repository.approvalTransaction(id, requestApproval)
                    Log.d("RESPONSE VM TRAnS", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseApproval(
                            code = 400,
                            data = null,
                            message = "Email or Password invalid!",
                        )
                } finally {
                    emit(response)
                }
            }
        }
}
