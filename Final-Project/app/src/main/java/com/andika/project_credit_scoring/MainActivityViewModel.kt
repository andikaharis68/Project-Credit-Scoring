package com.andika.project_credit_scoring

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _bottomVisibility: MutableLiveData<Int> = MutableLiveData(View.VISIBLE)

    val bottomVisibility: LiveData<Int> = _bottomVisibility

    fun hideBottomVav(boolean: Boolean) {
        if (boolean) {
            _bottomVisibility.postValue(View.GONE)
        } else {
            _bottomVisibility.postValue(View.VISIBLE)
        }
    }

}