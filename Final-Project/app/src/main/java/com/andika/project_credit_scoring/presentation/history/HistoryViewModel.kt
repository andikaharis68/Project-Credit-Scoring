package com.andika.project_credit_scoring.presentation.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.model.history.ListHistory
import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.repositories.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

//@Inject constructor

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HistoryRepository) : ViewModel(), HistoryClickListener {

    private var _detailLiveData = MutableLiveData<ListHistory?>()

    val detailLiveData: MutableLiveData<ListHistory?>
        get() {
            return _detailLiveData
        }

    fun getALlHistory() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseHistory? = null
                try {
                    response = repository.getAllHistory()
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        ResponseHistory(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }
    fun getApprovedHistory() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseHistory? = null
                try {
                    response = repository.getApprovedHistory()
                    for (i in response.data?.list!!){
                        Log.d("RESPONSE", "$response")
                    }
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        ResponseHistory(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }
    fun getRejectedHistory() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseHistory? = null
                try {
                    response = repository.getRejectedHistory()
                    Log.d("RESPONSE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        ResponseHistory(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    override fun onDetail(history: ListHistory?) {
        _detailLiveData.postValue(history)
    }


}