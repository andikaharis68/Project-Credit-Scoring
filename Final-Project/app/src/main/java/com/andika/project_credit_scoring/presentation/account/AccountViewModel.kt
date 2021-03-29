package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import androidx.lifecycle.*

import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
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

    private var _deleteLiveData = MutableLiveData<String?>()

    val deleteLiveData: MutableLiveData<String?>
        get() {
            return _deleteLiveData
        }


    fun getALlAccount() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseAccount? = null
                try {
                    response = repository.getAllAccount()
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseAccount(
                            code = 100,
                            data = null,
                            message = "Failed connect to server",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun addAccount(requestAccount: RequestAddAccount) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseAccount? = null
                Log.d("REQUEST ADD ACCOUNT", "$requestAccount")
                try {
                    response = repository.addAccount(requestAccount)

                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseAccount(
                            code = 100,
                            data = null,
                            message = "Failed connect to server",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    override fun onDelete(id: String) {
        deleteLiveData.postValue(id)
    }

    fun deleteAccount(id : String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseAccount? = null
                try {
                    response = repository.deleteAccount(id)
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseAccount(
                            code = 400,
                            data = null,
                            message = "Email or Password invalid!",
                        )
                } finally {
                    emit(response)
                }
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

