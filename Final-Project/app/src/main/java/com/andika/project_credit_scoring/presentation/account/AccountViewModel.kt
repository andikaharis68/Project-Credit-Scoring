package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.repositories.AccountRepository
import com.andika.project_credit_scoring.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(@ServiceAccount val repository: AccountRepository) : ViewModel(), AccountClickListener{
    private var _accountsLiveData = MutableLiveData<ResourceState>()
    private var _accountLiveData = MutableLiveData<Account>()

    val accountsLiveData: MutableLiveData<ResourceState>
        get() {
            return _accountsLiveData
        }

    val accountLiveData: LiveData<Account>
        get() {
            return _accountLiveData
        }

    fun getALlAccount() {
        CoroutineScope(Dispatchers.IO).launch {
            _accountsLiveData.postValue(ResourceState.loading())
            val response = repository.getAccount()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("DATA", "$it")
                    _accountsLiveData.postValue(ResourceState.success(it))
                }
            } else {
                _accountsLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

    fun addAccount(account: Account){
        repository.addAccount(account)
    }

    override fun onUpdate(account: Account) {
        TODO("Not yet implemented")
    }

}