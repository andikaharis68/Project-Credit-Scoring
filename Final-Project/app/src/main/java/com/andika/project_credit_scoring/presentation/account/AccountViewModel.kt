package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import androidx.lifecycle.*
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.entity.History
import com.andika.project_credit_scoring.repositories.AccountRepository
import com.andika.project_credit_scoring.util.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(@ServiceAccount val repository: AccountRepository) :
    ViewModel(), AccountClickListener {
    private var _accountsLiveData = MutableLiveData<ResourceState>()
    private var _accountLiveData = MutableLiveData<ResourceState>()

    val accountsLiveData: MutableLiveData<ResourceState>
        get() {
            return _accountsLiveData
        }

    val accountLiveData: LiveData<ResourceState>
        get() {
            return _accountLiveData
        }

    fun getALlAccount() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: Account? = null
                try {
                    response = repository.getAllAccount()
                    Log.d("RESPONSE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        Account(
                            code = 400,
                            data = null,
                            message = "Email or Password invalid!",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun addAccount(account: Account) {
        CoroutineScope(Dispatchers.IO).launch {
            _accountLiveData.postValue(ResourceState.loading())
            val response = repository.addAccount(account)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("DATA", "$it")
                    _accountLiveData.postValue(ResourceState.success(it))
                }
            } else {
                _accountLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

    override fun onUpdate(account: Account) {
        TODO("Not yet implemented")
    }
}
