package com.andika.project_credit_scoring.presentation.roles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.andika.project_credit_scoring.model.history.ResponseHistory
import com.andika.project_credit_scoring.model.roles.RequestRole
import com.andika.project_credit_scoring.model.roles.ResponseRole
import com.andika.project_credit_scoring.repositories.RoleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class RoleViewModel @Inject constructor(private val repository: RoleRepository) : ViewModel(), RoleListClickListener {

    private var _deleteLiveData = MutableLiveData<String?>()

    val deleteLiveData: MutableLiveData<String?>
        get() {
            return _deleteLiveData
        }

    fun getALlRole() =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseRole? = null
                try {
                    response = repository.getRole()
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
                    response =
                        ResponseRole(
                            code = 100,
                            data = null,
                            message = "Failed connect to server!",
                        )
                } finally {
                    emit(response)
                }
            }
        }

    fun addRole(requestRole: RequestRole) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseRole? = null
                try {
                    response = repository.addRole(requestRole)
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
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

    fun deleteRole(id : String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            withTimeout(5000) {
                var response: ResponseRole? = null
                try {
                    response = repository.deleteRole(id)
                } catch (e: Exception) {
                    Log.d("ERROR","$e")
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


    override fun onDelete(id: String) {
        deleteLiveData.postValue(id)
    }
}