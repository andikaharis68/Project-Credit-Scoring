package com.andika.project_credit_scoring.presentation.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.repositories.HistoryRepository
import com.andika.project_credit_scoring.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//@Inject constructor

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HistoryRepository) : ViewModel(), HistoryClickListener {
    private var _historiesLiveData = MutableLiveData<ResourceState>()

    val historiesLiveData: MutableLiveData<ResourceState>
        get() {
            return _historiesLiveData
        }

    fun getALlHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            _historiesLiveData.postValue(ResourceState.loading())
            val response = repository.getAllHistory()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("DATA", "$it")
                    _historiesLiveData.postValue(ResourceState.success(it))
                }
            } else {
                _historiesLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

}