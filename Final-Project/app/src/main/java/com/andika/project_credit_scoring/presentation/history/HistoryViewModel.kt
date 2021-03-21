package com.andika.project_credit_scoring.presentation.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.entity.ListHistory
import com.andika.project_credit_scoring.repositories.HistoryRepository
import com.andika.project_credit_scoring.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.log

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
                var response: History? = null
                try {
                    response = repository.getAllHistory()
                    Log.d("RESPONSE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        History(
                            code = 400,
                            data = null,
                            message = "Email or Password invalid!",
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