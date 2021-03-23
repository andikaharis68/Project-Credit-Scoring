package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import androidx.lifecycle.*
import com.andika.project_credit_scoring.entity.Account
import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.entity.ListAccount
import com.andika.project_credit_scoring.entity.ListHistory
import com.andika.project_credit_scoring.entity.RequestAccount
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
    private var _accountLiveData = MutableLiveData<ResourceState>()
    private var _deleteLiveData = MutableLiveData<String?>()

    val activateAccountLiveData: LiveData<ResourceState>
        get() {
            return _accountLiveData
        }

    val deleteLiveData: MutableLiveData<String?>
        get() {
            return _deleteLiveData
        }

    fun getALlAccount() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: Account? = null
                try {
                    response = repository.getAllAccount()
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
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

    fun addAccount(requestAccount: RequestAccount) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.addAccount(requestAccount)
            if(response.isSuccessful) {
                _accountLiveData.postValue(ResourceState.success(response.body()))
            } else {
                _accountLiveData.postValue(ResourceState.fail("error"))
            }
        }
    }

    override fun onDelete(id: String) {
        deleteLiveData.postValue(id)
    }

    fun deleteAccount(id : String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAccount(id)
        }
    }

//    fun checkValidation(
//        name: String,
//        email: String,
//        username: String,
//        password: String,
//        firstName: String
//    ) {
//        var validation = ResponseValidation("", "")
//        if (!email.validEmail()) {
//            validation.message = "Emial is not Valid"
//            validation.status = "VALIDATION_EMAIL"
//        } else if (username.length < 6 && username.length > 12) {
//            validation.message = "Password is not Valid min 6 character"
//            validation.status = "VALIDATION_USERNAME"
//        } else {
//            validation.message = ""
//            validation.status = "VALIDATION_SUCCESS"
//        }
//        _liveValidation.postValue(validation)
//    }
}

