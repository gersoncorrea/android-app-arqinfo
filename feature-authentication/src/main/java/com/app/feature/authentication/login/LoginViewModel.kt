package com.app.feature.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.LoginRepository
import com.app.feature.authentication.remote.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginViewModel(
    private val view: LoginContract.View,
    private val repository: LoginRepository
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> get() = _login

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    fun getAll() {
        viewModelScope.launch {
            when (val apiResponse = repository.getLogin()) {
                is ApiResult.Success -> {
                    apiResponse.data.let {
                        _login.value = it
                    }
                }
                is ApiResult.Error -> {
                    _error.value = apiResponse.exception
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}