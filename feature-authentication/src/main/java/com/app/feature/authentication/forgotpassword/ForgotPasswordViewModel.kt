package com.app.feature.authentication.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.core.network.ApiResult
import com.app.feature.authentication.domain.ForgotPasswordModel
import com.app.feature.authentication.domain.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val repository: LoginRepository
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _screenResponse = MutableLiveData<ForgotPasswordModel>()
    val screenResponse: LiveData<ForgotPasswordModel> = _screenResponse

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun getScreenPayload() {
        _loading.value = true
        viewModelScope.launch {
            when (val apiResponse = repository.getForgotPassword()) {
                is ApiResult.Success -> {
                    apiResponse.data.let {
                        _loading.value = false
                        _screenResponse.value = it
                    }
                }

                is ApiResult.Error -> {
                    _loading.value = false
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