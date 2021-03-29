package com.andika.project_credit_scoring.presentation.login

import android.util.Log
import androidx.lifecycle.*
import com.andika.project_credit_scoring.di.qualifier.PostAuth
import com.andika.project_credit_scoring.login.RequestLogin
import com.andika.project_credit_scoring.login.ResponseLogin
import com.andika.project_credit_scoring.login.ValidationLogin
import com.andika.project_credit_scoring.model.roles.ResponseRole
import com.andika.project_credit_scoring.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@PostAuth val repository: LoginRepository) : ViewModel() {
    private val _validation: MutableLiveData<ValidationLogin> = MutableLiveData()

    fun getValidation(): LiveData<ValidationLogin> {
        return _validation
    }

    fun postLogin(requestLogin: RequestLogin) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseLogin? = null
                try {
                    response = repository.postLogin(requestLogin)
                    Log.d("REQ LOGIN","$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseLogin(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun getRole() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseRole? = null
                try {
                    response = repository.getRole()
                    Log.d("ROLE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseRole(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun validation(requestLogin: RequestLogin) {
        if (requestLogin.password.length >= 6) {
            _validation.postValue(ValidationLogin(password = true, username = true))
        } else {
            _validation.postValue(ValidationLogin(password = false, username = true))
        }
    }
}

