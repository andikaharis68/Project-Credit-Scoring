package com.andika.project_credit_scoring.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.di.qualifier.PostAuth
import com.andika.project_credit_scoring.model.roles.ResponseRole
import com.andika.project_credit_scoring.model.user.RequestPassword
import com.andika.project_credit_scoring.model.user.RequestUser
import com.andika.project_credit_scoring.model.user.ResponsePassword
import com.andika.project_credit_scoring.model.user.ResponseUser
import com.andika.project_credit_scoring.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(@PostAuth val repository: LoginRepository) : ViewModel() {

    fun getUser() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseUser? = null
                try {
                    response = repository.getUser()
                    Log.d("ROLE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseUser(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun editUser(requestUser: RequestUser) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseUser? = null
                try {
                    response = repository.editUser(requestUser)
                    Log.d("ROLE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponseUser(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun editPassword(requestPassword: RequestPassword) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponsePassword? = null
                try {
                    response = repository.editPassword(requestPassword)
                    Log.d("ROLE","$response")
                } catch (e: Exception) {
                    Log.d("ERROR", "$e")
                    response =
                        ResponsePassword(
                            code = 100,
                            data = null,
                            message = "Connection error please try again",
                        )
                } finally {
                    emit(response)
                }
            }
        }
}