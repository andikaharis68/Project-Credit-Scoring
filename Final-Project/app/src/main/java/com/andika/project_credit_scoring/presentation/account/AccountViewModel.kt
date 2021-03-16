package com.andika.project_credit_scoring.presentation.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andika.project_credit_scoring.account.Account
import com.andika.project_credit_scoring.account.AccountCoba
import com.andika.project_credit_scoring.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel (private val repository: AccountRepository) : ViewModel(), AccountClickListener{
    private var _accountLiveData = MutableLiveData<List<AccountCoba>>()

    val accountsLiveData: LiveData<List<AccountCoba>>
        get() {
            return _accountLiveData
        }

    fun getALlAccount() {
            _accountLiveData.postValue(repository.getAccount())
    }

    override fun onUpdate(account: Account) {
        TODO("Not yet implemented")
    }
}