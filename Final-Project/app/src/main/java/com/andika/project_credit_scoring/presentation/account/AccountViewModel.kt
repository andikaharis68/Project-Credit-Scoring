package com.andika.project_credit_scoring.presentation.account

import android.util.Log
import androidx.lifecycle.*

import com.andika.project_credit_scoring.di.qualifier.ServiceAccount
import com.andika.project_credit_scoring.model.account.RequestAddAccount
import com.andika.project_credit_scoring.model.account.ResponseAccount
import com.andika.project_credit_scoring.model.roles.ResponseRole
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

    fun getALlAccountVerified() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseAccount? = null
                try {
                    response = repository.getAllAccountVerified()
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

    fun getALlAccountNotVerified() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseAccount? = null
                try {
                    response = repository.getAllAccountNotVerified()
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
    fun getRole() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseRole  ? = null
                try {
                    response = repository.getRole()
                    Log.d("RESPONSE", "$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseRole(
                            code = 100,
                            data = null,
                            message = "Failed connect to server",
                        )
                } finally {
                    emit(response)
                }
            }
        }
}

